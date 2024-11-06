package controller;

import model.Coche;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorCoches {
    ArrayList<Coche> listaCoches = new ArrayList<>();
    Coche coche = new Coche();
    private int contadorId = 1;
    Scanner scanner = new Scanner(System.in);

    public GestorCoches() {
        listaCoches = new ArrayList<>();
    }

    //verificar si existe el fichero
    public ArrayList<Coche> verificarFile(String path) {

        File file = new File(path);

        if (file.exists()) {
            System.out.println("El archivo existe: " + path);
            // llamada al metodo "leerCochesDesdeArchivo()"
            listaCoches = leerCochesDesdeArchivo(file);
            // Mostrar los coches leídos
            for (Coche coche : listaCoches) {
                System.out.println(coche);
            }
        } else {
            System.out.println("El archivo no existe: " + path);
            // No hay nada que hacer si el archivo no existe
        }
        return listaCoches;
    }

    //metodo para leer coches desde el archivo
    public ArrayList<Coche> leerCochesDesdeArchivo(File file) {
        ArrayList<Coche> cochesLeidos = new ArrayList<>();

        // Intentar leer los objetos desde el archivo
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                // Leer un objeto Coche y añadirlo a la lista
                listaCoches = (ArrayList<Coche>) objectInputStream.readObject();
                //agregar a la lista
                cochesLeidos.add(coche);
            }
        } catch (EOFException e) {
            // Fin de archivo alcanzado, esto es normal
            System.out.println("Lectura completa del archivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return cochesLeidos;
    }

//añadir coche
        public boolean addCoche(Coche nuevoCoche) {

        for (Coche coche : listaCoches) {
//Verificar que las matriculas no se repiten
            if (coche.getMatricula().equals(nuevoCoche.getMatricula())) {

                System.out.println("La matricula: " + nuevoCoche.getMatricula() + " ya existe.");
                return false;
            }
        }
//Si no se repiten, se sale del bucle y se añade el obj al ArrayList
        nuevoCoche.setId(contadorId);
        contadorId++;
        listaCoches.add(nuevoCoche);
        return true;
    }
//borrar coche por id
        public void borrarCoche (int id){

            boolean cocheEliminado = false; //comprobar si se eliminó el coche

            // Iterar sobre la lista de coches
            for (int i = 0; i < listaCoches.size(); i++) {
                Coche coche = listaCoches.get(i);
                if (coche.getId() == id) {
                    listaCoches.remove(i); // Eliminar el coche
                    cocheEliminado = true; // Actualizar
                    System.out.println("Coche con ID " + id + " ha sido eliminado.");
                    break; // Salir del bucle una vez que se elimina

                }


            }
        }
//consultar coche por id
        public Coche consultarCocheId (int id){

            for (Coche coche : listaCoches) {
                if (coche.getId() == id) {
                    System.out.println("Coche seleccionado: " + coche);
                    return coche;
                }
            }
            System.out.println("No se encontró ningún coche con ID " + id);
            return null;
        }
//listar todos los coches
        public void listarCoches () {
            for (Coche coche : listaCoches) {
                System.out.println(coche);
            }

        }
//gurdar todos los coches en un fichero.dat
        public void guardarCochesFichero(String path){

            File file = new File(path);

            ObjectOutputStream objectOutputStream = null;

            try {
                file.createNewFile();
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                objectOutputStream.writeObject(listaCoches);
                System.out.println("Coches correctamente guardados.");
            } catch (IOException e) {
                System.out.println("Error en el fichero.");
            }finally {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el fujo.");
                }
            }
        }
//leer fichero.dat
        public void leerFichero(String path){
        File file = new File(path);

        ObjectInputStream objectInputStream = null;

            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(file));

                listaCoches = (ArrayList<Coche>) objectInputStream.readObject();

                for (Coche coche : listaCoches){
                    coche.mostrarDatosCoche();
                }
            } catch (ClassNotFoundException  | IOException e) {
                System.out.println("Error al leer el fichero.");
            }finally {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    System.out.println("Error al cerra el flujo.");
                }
            }

        }
//exportar los coches a un archivo.dat
        public void exportarCSV(String path){

            File file = new File(path);

            PrintWriter printWriter = null;

            try {
                printWriter = new PrintWriter(new FileWriter(file));

                printWriter.println("Matricula;Marca;Modelo;Color");

                for (Coche coche : listaCoches){
                    printWriter.println(coche.getMatricula() + ";" + coche.getMarca() + ";" + coche.getModelo() + ";" + coche.getColor());

                }
                System.out.println("Coche/s exportados correctamente");
            } catch (IOException e) {
                System.out.println("Error al escribir el fichero.");
            }finally {
                printWriter.close();
            }


        }
}
