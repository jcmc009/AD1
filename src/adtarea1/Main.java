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
                    gestor.borrarUsuario();
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

}
