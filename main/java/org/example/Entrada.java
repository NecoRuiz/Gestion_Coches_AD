package org.example;

import controller.GestorCoches;
import model.Coche;

import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {

        GestorCoches gestorCoches = new GestorCoches();
        ArrayList<Coche> coches = gestorCoches.verificarFile("src/main/java/resources/coches.dat");

        int opcion = 0;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("1. Añadir coche");
            System.out.println("2. Borrar coche por id");
            System.out.println("3. Consultar coche por id");
            System.out.println("4. Listar coches");
            System.out.println("5. Salir");
            System.out.println("6. Exportar a .csv");
            System.out.println("Escoge una opción:");
            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println("Introduce la matricula: ");
                    String matricula = sc.next();
                    System.out.println("Introduce la marca: ");
                    String marca = sc.next();
                    System.out.println("Introduce el modelo: ");
                    String modelo = sc.next();
                    System.out.println("Introduce el color: ");
                    String color = sc.next();

                    // Instancia de coche
                    Coche nuevoCoche = new Coche(matricula, marca, modelo, color);

                    // Llamada del método
                    if (gestorCoches.addCoche(nuevoCoche)){
                        System.out.println("Coche añadido correctamente: " + nuevoCoche);
                    } else {
                        System.out.println("El coche no se añadió correctamente ya que la matrícula ya existe.");
                    }
                    break;

                case 2:
                    System.out.println("Introduce un id para poder borrar un coche: ");
                    int idCocheBorrado = sc.nextInt();
                    gestorCoches.borrarCoche(idCocheBorrado);
                    break;

                case 3:
                    System.out.println("Introduce un id para consultar un coche: ");
                    int idConsulta = sc.nextInt();
                    gestorCoches.consultarCocheId(idConsulta);
                    break;

                case 4:
                    gestorCoches.listarCoches();
                    break;

                case 5:
                    // Guardar coches en el fichero y terminar el programa
                    gestorCoches.guardarCochesFichero("src/main/java/resources/coches.dat");
                    gestorCoches.leerFichero("src/main/java/resources/coches.dat");
                    System.out.println("Coches guardados correctamente.");
                    break;

                case 6:
                    // Exportar a archivo CSV y salir
                    gestorCoches.exportarCSV("src/main/java/resources/archivo.csv");
                    System.out.println("Coches exportados a CSV correctamente.");
                    break;

                default:
                    System.out.println("Opción no válida, por favor elija entre 1-6.");
            }

        } while (opcion != 6);  // Terminamos el programa al elegir la opción 6

        sc.close();
        System.out.println("El programa ha terminado.");
    }

}