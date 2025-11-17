package data_access.T1EX;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import data_access.T1.ScannerEx;

public class AD_EX6_R {
    public static String leerArchivo(String pathFile) {
        String allText = "";
        try (Scanner sc = new Scanner(new File(pathFile))) {
            while (sc.hasNextLine()) {
                allText += sc.nextLine();
            }
        } catch (IOException e) {
        }
        return allText;
    }

    public static void crearArchivoXChars(int numeroCharsPorArchivo, String fileName, String contenidoEscribir) {
        int numeroArchivos = contenidoEscribir.length() / numeroCharsPorArchivo;
        int inicio = 0;
        int maximo = numeroCharsPorArchivo;
        for (int i = 0; i < numeroArchivos; i++) {
            try (FileWriter fr = new FileWriter(fileName + i)) {
                fr.write(contenidoEscribir.substring(inicio, maximo));
            } catch (IOException e) {
            }
            inicio += numeroCharsPorArchivo;
            maximo += numeroCharsPorArchivo;
        }
    }

    public static void generarArchivoPorLineas(int numeroLineas, String contenido){
        String[] contenidoSplited = contenido.split(" ");
        for (int i = 0; i < contenidoSplited.length; i++) {
            try (FileWriter fWriter = new FileWriter(new File("archivo" + i + ".txt"))) {
                fWriter.write(contenidoSplited[i]);
            } catch (IOException e) {
            }
        }
        System.out.println(contenidoSplited.length);
    }

    public static void main(String[] args) {
        //crearArchivoXChars(10, "Ad_ex6.txt", leerArchivo("a.txt"));
        generarArchivoPorLineas(5, leerArchivo("a.txt"));
    }
}

// 6. Crea un programa que realice las siguientes acciones:
// ➢ Dividir un fichero en función de:
// • De un número n de caracteres (en cada fichero generado debe poseer n
// caracteres).
// • De un número l de líneas (en cada fichero generado debe poseer l líneas).
// ➢ Unir ficheros: dada una lista de ficheros generará un nuevo fichero que
// resultara de la unión de los anteriores.