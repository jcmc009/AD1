/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtarea1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Jc
 */
public class GestionUsuario {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();
//"Usuario{"
//                + "identificador='" + identificador + '\''
//                + "contraseña='" + contrasenya + '\''
//                + ", direccion='" + direccion + '\''
//                + ", añoNacimiento=" + anyoNacimiento
//                + '}';

    public void agregarUsuario() {
        Scanner scannerAgregar = new Scanner(System.in);
        String id = "";

        do {
            System.out.print("Inserta el identificador del usuario a agregar: ");
            id = scannerAgregar.nextLine();
        } while (id.isEmpty());

        System.out.print("Inserta la contraseña del usuario a agregar: ");
        String contrasenya = scannerAgregar.nextLine();

        System.out.print("Inserta la dirección del usuario a agregar: ");
        String direccion = scannerAgregar.nextLine();

        boolean entradaValida = false;
        int anyoNacimiento = 0;
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

        Usuario user = new Usuario(id, contrasenya, direccion, anyoNacimiento);
        listaUsuarios.add(user);
        System.out.println("Usuario agregado correctamente.");

    }

    public void mostrarUsuarios() {
        System.out.println("Hay los siguientes usuarios en el sistema:");
        if (listaUsuarios.isEmpty()) {
            System.out.println("No hay usuarios a mostrar");

        } else {
            for (Usuario usuario : listaUsuarios) {
                System.out.println(usuario);
            }
        }
    }

    public void borrarUsuario() {
        Scanner scannerBorrado = new Scanner(System.in); // Crear scanner local para esta operación

        System.out.println("Inserta el identificador del usuario a borrar:");
        String opcion = scannerBorrado.nextLine(); // Leer identificador

        Iterator<Usuario> it = listaUsuarios.iterator();
        boolean eliminado = false;
        while (it.hasNext()) {

            Usuario usuario = it.next();
            if (usuario.getIdentificador().equals(opcion)) {
                it.remove();
                eliminado = true;
                System.out.println("Usuario eliminado correctamente.");
                break; // salir del bucle tras eliminar
            }
            if (!eliminado) {
                System.out.println("Usuario no encontrado.");
            }
        }

    }

}
