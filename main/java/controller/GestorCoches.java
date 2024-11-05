package controller;

import model.Coche;

import java.io.*;
import java.util.ArrayList;

public class GestorCoches {
    ArrayList<Coche> listaCoches = new ArrayList<>();
    Coche coche = new Coche();

    public GestorCoches() {
        listaCoches = new ArrayList<>();
    }
   //verificar si existe el fichero
    public ArrayList<Coche> verificarFile(String path) {

        File file = new File(path);

        if (file.exists()) {
            System.out.println("El archivo existe: " + path);
            // Leer los objetos Coche desde el archivo
            ArrayList<Coche> listaCoches = leerCochesDesdeArchivo(file);
            // Mostrar los coches leídos
            for (Coche coche : listaCoches) {
                System.out.println(coche);
            }
        } else {
            System.out.println("El archivo no existe: " + path);
            // No hay nada que hacer si el archivo no existe
        }
        return null;
    }

    //metodo para leer coches desde el archivo
    public ArrayList<Coche> leerCochesDesdeArchivo(File file) {


        // Intentar leer los objetos desde el archivo
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                // Leer un objeto Coche y añadirlo a la lista
                Coche coche = (Coche) objectInputStream.readObject();
                //agregar a la lista
                listaCoches.add(coche);
            }
        } catch (EOFException e) {
            // Fin de archivo alcanzado, esto es normal
            System.out.println("Lectura completa del archivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return listaCoches;
    }

    //metodo para añadir un coche
    public void addCoche(Coche coche){

        coche.setId(listaCoches.size() +1);
        listaCoches.add(coche);

    }

    public void borrarCoche(int id) {

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

    public Coche consultarCocheId(int id) {

        for (Coche coche : listaCoches){
            if (coche.getId() == id) {
                System.out.println("Coche seleccionado: " + coche);
                return coche;
        }
        }
        System.out.println("No se encontró ningún coche con ID " + id);
        return null;
    }

}
