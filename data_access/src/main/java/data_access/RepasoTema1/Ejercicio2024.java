package data_access.RepasoTema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio2024 {
    public static  ArrayList<String> leerArchivo(String nombreArchivo){
        if (!(new File(nombreArchivo).exists() || new File(nombreArchivo).isFile())) {
            return null;
        }
        ArrayList<String> lineas = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nombreArchivo))) {
            while (sc.hasNextLine()) {
                lineas.add(sc.nextLine());
            }
        } catch (IOException e) {
        }
        lineas.remove(0);
        return lineas;
    }

    public static String escogerLinea(ArrayList<String> lineas, int posicionLinea){
        return lineas.get(posicionLinea - 1);
    }

    public static String formatearCadena(String cadena){
        return cadena.replace("\t", ",");
    }

    public static String cadenaMultiplicada(String cadena){
        String[] cad = cadena.split(",");
        float a = Float.parseFloat(cad[0]);
        float b = Float.parseFloat(cad[1]);
        float suma = a + b;
        return suma + " " + cad[2];
    }

    public static void main(String[] args) throws FileNotFoundException {
    //    System.out.println(leerArchivo("juegos.txt"));
    //    System.out.println(escogerLinea(leerArchivo("juegos.txt"), 2));
        System.out.println(cadenaMultiplicada(formatearCadena(escogerLinea(leerArchivo("juegos.txt"), 1))));
    }
}
