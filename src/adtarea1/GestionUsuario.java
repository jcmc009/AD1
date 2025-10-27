/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtarea1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que gestiona una lista de objetos Usuario, permitiendo agregar, borrar,
 * mostrar, guardar y cargar usuarios desde archivos binarios o de texto.
 *
 * @author José Carlos Manjón Carrasco
 */
public class GestionUsuario {

    // Lista principal de usuarios en memoria
    private List<Usuario> usuarios;
    // Identificador de versión para la serialización
    private static final long serialVersionUID = 42L;

    // Rutas de los archivos
    private String rutaArchivoDat = System.getProperty("user.dir") + "/resources/Usuario.dat";
    private String rutaArchivoTxt = System.getProperty("user.dir") + "/resources/Usuario.txt";
    // Indicador de si hay cambios sin guardar
    private boolean cambiosPendientes = false;

    // Métodos para controlar el estado de cambios
    public boolean hayCambiosPendientes() {
        return cambiosPendientes;
    }

    public void marcarCambios() {
        cambiosPendientes = true;
    }

    public void limpiarCambios() {
        cambiosPendientes = false;
    }

    /**
     * Constructor por defecto: inicializa la lista de usuarios vacía.
     */
    public GestionUsuario() {
        usuarios = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Constructor alternativo que permite establecer una ruta personalizada
     * para el archivo binario.
     */
    public GestionUsuario(String archivo) {
        this.rutaArchivoDat = archivo;
    }

    /**
     * Solicita los datos de un nuevo usuario por consola y lo agrega a la
     * lista.
     */
    public void agregarUsuario() {
        Scanner scannerAgregar = new Scanner(System.in);
        String id = "", contrasenya = "", direccion = "";
        int anyoNacimiento = 0;
// Validación de identificador no vacío
        do {
            System.out.print("Inserta el identificador del usuario a agregar: ");
            id = scannerAgregar.nextLine();
        } while (id.isEmpty());
// Captura de datos restantes
        do {
            System.out.print("Inserta la contraseña del usuario a agregar: ");
            contrasenya = scannerAgregar.nextLine();
        } while (contrasenya.isEmpty());

        do {
            System.out.print("Inserta la dirección del usuario a agregar: ");
            direccion = scannerAgregar.nextLine();
        } while (direccion.isEmpty());

        boolean entradaValida = false;
        // Validación de año de nacimiento
        while (!entradaValida) {
            System.out.print("Inserta el año de nacimiento del usuario a agregar: ");
            String entrada = scannerAgregar.nextLine();
            try {
                anyoNacimiento = Integer.parseInt(entrada);
                if (anyoNacimiento >= 0) {
                    entradaValida = true;
                } else {
                    System.out.println("Año no válido");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número entero.");
            }
        }
        // Creación y almacenamiento del usuario
        Usuario user = new Usuario(id, contrasenya, direccion, anyoNacimiento);
        usuarios.add(user); // Aquí se guarda en la lista
        marcarCambios();//Marcamos que hay cambios pendientes, pues acabamos de agregar un usuario
        System.out.println("Usuario agregado correctamente.");

    }

    /**
     * Muestra por consola todos los usuarios almacenados en la lista.
     */
    public void leerConsola() {
        System.out.println("Hay los siguientes usuarios en el sistema:");
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios a mostrar");

        } else {
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }

    /**
     * Solicita un identificador y elimina el usuario correspondiente de la
     * lista.
     */
    public void borrarUsuario() {
        Scanner scannerBorrado = new Scanner(System.in); // Crear scanner local para esta operación

        System.out.println("Inserta el identificador del usuario a borrar:");
        String opcion = scannerBorrado.nextLine();

        Iterator<Usuario> it = usuarios.iterator();
        boolean eliminado = false;
        while (it.hasNext()) {

            Usuario usuario = it.next();
            if (usuario.getIdentificador().equals(opcion)) {
                it.remove();
                eliminado = true;
                System.out.println("Usuario eliminado correctamente.");
                marcarCambios();
                break; // salir del bucle tras eliminar
            }

        }
        if (!eliminado) {
            System.out.println("Usuario no encontrado.");
        }

    }

    /**
     * Carga la lista de usuarios desde el archivo binario, previa confirmación
     * si hay cambios sin guardar.
     */
    public List<Usuario> leerListaDat() {
        // Verifica si hay cambios pendientes antes de cargar
        if (cambiosPendientes) {
            System.out.println("Se ha realizado cambios que no ha guardado en disco.");
            System.out.println("Si continúa la carga del archivo se restaurarán los datos de disco y se perderán los cambios no guardados.");
            System.out.print("¿Desea continuar con la carga y restaurar los datos del archivo? (s/n): ");
            // Captura y valida la respuesta del usuario
            Scanner scanner = new Scanner(System.in);
            String respuesta = scanner.nextLine().trim().toLowerCase();

            while (!respuesta.equals("s") && !respuesta.equals("n")) {
                System.out.print("Respuesta no válida. Introduzca 's' para continuar o 'n' para cancelar: ");
                respuesta = scanner.nextLine().trim().toLowerCase();
            }
            // Si el usuario cancela, se mantiene la lista actual
            if (respuesta.equals("n")) {
                System.out.println("Carga cancelada. Vuelva al menú para guardar los cambios si lo desea.");
                return usuarios; // mantiene la lista actual sin sobrescribir
            }
        }
        // Intenta leer el archivo binario
        try {
            FileInputStream fichero = new FileInputStream(rutaArchivoDat);
            ObjectInputStream objetostream = new ObjectInputStream(fichero);
            Object objeto = objetostream.readObject();
            objetostream.close();
// Intenta leer el archivo binario
            if (objeto instanceof List<?>) {
                List<?> lista = (List<?>) objeto;
// Verifica que la lista contenga objetos de tipo Usuario
                if (!lista.isEmpty() && lista.get(0) instanceof Usuario) {
                    List<Usuario> listaUsuarios = new ArrayList<>();
                    for (Object o : lista) {
                        listaUsuarios.add((Usuario) o);
                    }
// Muestra los usuarios cargados
                    System.out.println("Leo del archivo USUARIO.dat\nListado de Usuarios guarddados en el fichero :");
                    for (Usuario u : listaUsuarios) {
                        System.out.println(u);
                    }

                    return listaUsuarios;
                } else {
                    System.out.println("La lista no contiene objetos de tipo Usuario.");
                }
            } else {
                System.out.println("El objeto leído no es una lista.");
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error al leer el archivo: " + ex.getMessage());
        }
// Si ocurre un error o el archivo no es válido, se devuelve una lista vacía
        return new ArrayList<>(); // Devuelve lista vacía si falla
    }

    /**
     * Guarda la lista de usuarios en un archivo binario (.dat). Si la operación
     * es exitosa, se limpia el indicador de cambios pendientes.
     *
     * @param listaUsuarios Lista de usuarios a guardar
     */
    public void escribirListaDat(List<Usuario> listaUsuarios) throws IOException {
        // Ruta del archivo donde se guardará la lista
        File archivoDat = new File(rutaArchivoDat);
        // Crea los flujos de salida para escribir el archivo binario
        FileOutputStream fichero = new FileOutputStream(new File(rutaArchivoDat));
        ObjectOutputStream ficheroSalida = new ObjectOutputStream(fichero); // Escribo el objeto usuario en el fichero 
        // Verifica si la lista está vacía o nula antes de intentar guardar
        if (listaUsuarios == null || listaUsuarios.isEmpty()) {
            System.out.println("La lista de usuarios está vacía. No se puede guardar.");
            return;
        }
        try {
            // Mensaje según si el archivo existía previamente
            if (!archivoDat.exists()) { // Abrir fichero para escribirListaDat en él, en la ruta que me interesa 
                // Escribe el objeto (lista de usuarios) en el archivo
                ficheroSalida.writeObject(listaUsuarios);
                // Cierro el fichero 
                ficheroSalida.close();
                System.out.println("No hay datos previos,se creará un archivo en " + rutaArchivoDat);
                // Limpia el indicador de cambios pendientes
                limpiarCambios();
                System.out.println("Lista guardada correctamente.");
            } else {
                ficheroSalida.writeObject(listaUsuarios); // Cierro el fichero 
                ficheroSalida.close();
                // System.out.println("No hay datos previos,se creará un archivo en " + rutaArchivoDat);
                limpiarCambios();
                System.out.println("Lista guardada correctamente.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir: " + ex.getMessage());
        }
    }

    /**
     * Método que escribe, en un archivo binario, un objeto Usuario
     * serializable.
     *
     * @param Usuario Objeto Usuario serializable para almacenar en el archivo
     * binario.
     */
    public void escribirListaTxt(List<Usuario> listaUsuarios) throws IOException {
        File archivo = new File(rutaArchivoTxt);
        if (listaUsuarios == null || listaUsuarios.isEmpty()) {
            System.out.println("La lista de usuarios está vacía. No se puede guardar.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            if (!archivo.exists()) {
                System.out.println("No hay datos previos, se creará un archivo en " + rutaArchivoTxt);
            }

            for (Usuario usuario : listaUsuarios) {
                writer.write(usuario.toString());
                writer.newLine(); // salto de línea entre usuarios
            }

            System.out.println("Lista de usuarios guardada como texto en " + rutaArchivoTxt);
        } catch (IOException ex) {
            System.out.println("Error al escribir en archivo de texto: " + ex.getMessage());

        }

    }

    public boolean comprobarCambiosPendientes() {
        if (cambiosPendientes) {
            System.out.println("Ha habido cambios en el programa que todavía no se han guardado.");
            System.out.println("Si desea guardarlos, ejecute la opción correspondiente del menú.");
            System.out.println("Si sale ahora, los cambios se perderán.");
            System.out.print("¿Está seguro de que desea salir sin guardar? (s/n): ");

            Scanner scanner = new Scanner(System.in);
            String respuesta = scanner.nextLine().trim().toLowerCase();

            while (!respuesta.equals("s") && !respuesta.equals("n")) {
                System.out.print("Respuesta no válida. Introduzca 's' para salir o 'n' para volver al menú: ");
                respuesta = scanner.nextLine().trim().toLowerCase();
            }

            if (respuesta.equals("n")) {
                System.out.println("Cancelando salida. Vuelva al menú para guardar los cambios.");
                return false; // no salir
            }
        }

        System.out.println("Saliendo del programa...");
        return true; // salir
    }

    void comprobarAntesCargar() {
        List<Usuario> usuariosCargados = leerListaDat(); // intenta cargar desde archivo

        // Si la lista cargada es distinta de la actual, se actualiza
        if (usuariosCargados != getUsuarios()) {
            getUsuarios().clear();
            getUsuarios().addAll(usuariosCargados);

        } else {
            System.out.println("No se ha realizado la carga. La lista actual se mantiene.");
        }
    }

    void comprobarAntesGuardar() {
        List<Usuario> lista = getUsuarios();

        if (lista == null || lista.isEmpty()) {
            System.out.println("La lista de usuarios está vacía. No se puede guardar.");
            return;
        }

        try {
            escribirListaDat(lista);
            System.out.println("Lista de usuarios guardada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la lista: " + e.getMessage());
        }
    }

}
