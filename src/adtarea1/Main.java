/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtarea1;

import java.util.Scanner;

/**
 *
 * @author Jc
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerBorrado = new Scanner(System.in);
        int opcion = 10;
        Usuario nuevoUsuarioDos = new Usuario("1", "ds2", "dsds", 1990);
        GestionUsuario gestor = new GestionUsuario();
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Agregar un usuario a la lista de usuarios.");
            System.out.println("2. Borrar un usuario a la lista de usuarios.");
            System.out.println("3. Guardar la lista de usuarios en un archivo (serialización).");
            System.out.println("4. Cargar la lista de usuarios desde el archivo (deserialización).");
            System.out.println("5. Mostrar  los usuarios en la consola.");
            System.out.println("6. Exportar a un fichero .txt la lista de usuarios.");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {

                System.out.println("Opción no válida. Intente de nuevo.");
                scanner.nextLine(); // Limpiar buffer
                continue; // Volver al inicio del bucle
            }

            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    gestor.agregarUsuario();
                    break;
                case 2:
                    borrar(gestor);
                    break;
                case 3:
                    System.out.println("Autor: José, estudiante de DAM.");
                    break;
                case 4:
                    System.out.println("Módulo actual: Desarrollo de Interfaces.");
                    break;
                case 5:

                    gestor.mostrarUsuarios();
                    break;
                case 6:
                    System.out.println(nuevoUsuarioDos.toString());
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 0);

    }

    public static void borrar(GestionUsuario gestor) {
        Scanner scannerBorrado = new Scanner(System.in); // Crear scanner local para esta operación

        System.out.println("Inserta el identificador del usuario a borrar:");
        String opcion = scannerBorrado.nextLine(); // Leer identificador

        gestor.eliminarUsuarioPorId(opcion);

        // No cierres el scanner si vas a seguir usando System.in en otros métodos
        // scannerBorrado.close(); ← solo si no se usa más adelante
    }

//    public static void agregar(GestionUsuario gestor) {
//        Scanner scannerAgregar = new Scanner(System.in);
//        String id = "";
//
//        do {
//            System.out.print("Inserta el identificador del usuario a agregar: ");
//            id = scannerAgregar.nextLine();
//        } while (id.isEmpty());
//
//        System.out.print("Inserta la contraseña del usuario a agregar: ");
//        String contrasenya = scannerAgregar.nextLine();
//
//        System.out.print("Inserta la dirección del usuario a agregar: ");
//        String direccion = scannerAgregar.nextLine();
//
//        boolean entradaValida = false;
//        int anyoNacimiento = 0;
//        while (!entradaValida) {
//            System.out.print("Inserta el año de nacimiento del usuario a agregar: ");
//            String entrada = scannerAgregar.nextLine();
//            try {
//                anyoNacimiento = Integer.parseInt(entrada);
//                if (anyoNacimiento >= 0) {
//                    entradaValida = true;
//                } else {
//                    System.out.println("Año no válido");
//                }
//
//            } catch (NumberFormatException e) {
//                System.out.println("Entrada no válida. Por favor, introduce un número entero.");
//            }
//        }
//
//        gestor.agregarUsuario(id, contrasenya, direccion, anyoNacimiento);
//        System.out.println("Usuario agregado correctamente.");
//    }
}
