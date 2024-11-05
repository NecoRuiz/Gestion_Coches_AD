package org.example;

import controller.GestorCoches;
import model.Coche;

import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {

        //creacion de coches.dat
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
            System.out.println("Escocge una opcion");
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

                    //instancia de coche
                    Coche nuevoCoche = new Coche(matricula, marca, modelo, color);

                    //llamada del metodo
                    gestorCoches.addCoche(nuevoCoche);


                    System.out.println("Coche añadido correctamente: " +nuevoCoche);
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
                    //metodoListar
                case 5:
                    //metodoSalir
                    break;

                default:
                    System.out.println("Opcion no valida, por favor escoja entre 1-5.");

            }
        } while (opcion != 5);
                sc.close();


    }
}