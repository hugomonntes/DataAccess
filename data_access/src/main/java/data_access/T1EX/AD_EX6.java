/*-
 * =====LICENSE-START=====
 * Java 11 Application
 * ------
 * Copyright (C) 2020 - 2025 Organization Name
 * ------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====LICENSE-END=====
 */
package data_access.T1EX;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AD_EX6 {
    public static String leerArchivoCompleto(String nombreFichero) throws FileNotFoundException {
        String texto = "";
        try (Scanner sc = new Scanner(new File(nombreFichero))) {
            while (sc.hasNextLine()) {
                texto += sc.nextLine() + "\n";
            }
        }
        return texto;
    }

    public static int calcularNumeroDeFicheros(String datos, int maxCaracteres) {
        return (int) datos.length() / maxCaracteres;
    }

    public static String[] dividirPorCaracteres(String datos, int numeroDeFicheros, int maxCaracteres) {
        String[] partes = new String[numeroDeFicheros];
        int inicio = 0;

        for (int i = 0; i < numeroDeFicheros; i++) {
            int fin = inicio + maxCaracteres;
            if (fin > datos.length()) {
                fin = datos.length();
            }
            partes[i] = datos.substring(inicio, fin);
            inicio = fin;
        }
        return partes;
    }

    public static void crearFicheros(String[] partes) throws IOException {
        for (int i = 0; i < partes.length; i++) {
            try (FileWriter fw = new FileWriter("Archivo" + i + ".txt")) {
                fw.write(partes[i]);
            }
        }
    }

    public static String[] dividirPorLineas(String nombreFichero, int maxLineas) throws FileNotFoundException {
        List<String> lineas = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nombreFichero))) {
            while (sc.hasNextLine()) {
                lineas.add(sc.nextLine());
            }
        }
        int numeroDeFicheros = (int) lineas.size() / maxLineas;

        String[] partes = new String[numeroDeFicheros];
        for (int i = 0; i < numeroDeFicheros; i++) {
            String bloque = "";
            int inicio = i * maxLineas;
            int fin = inicio + maxLineas;
            if (fin > lineas.size()) {
                fin = lineas.size();
            }

            for (int j = inicio; j < fin; j++) {
                bloque += lineas.get(j) + "\n";
            }

            partes[i] = bloque;
        }

        return partes;
    }

    public static void unirFicheros(String[] nombresFicheros, String nombreFicheroSalida) throws IOException {
        try (FileWriter fw = new FileWriter(nombreFicheroSalida)) {
            for (String nombre : nombresFicheros) {
                try (Scanner sc = new Scanner(new File(nombre))) {
                    while (sc.hasNextLine()) {
                        fw.write(sc.nextLine() + "\n");
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String datos = leerArchivoCompleto("a.txt");
        String[] partesPorCaracteres = dividirPorCaracteres(datos, calcularNumeroDeFicheros(datos, 5), 5);
        crearFicheros(partesPorCaracteres);

        String[] partesPorLineas = dividirPorLineas("a.txt", 3);
        crearFicheros(partesPorLineas);

        String[] ficherosAUnir = { "Archivo0.txt", "Archivo1.txt", "Archivo2.txt" };
        unirFicheros(ficherosAUnir, "ArchivoUnido.txt");
    }
}

// 6. Crea un programa que realice las siguientes acciones:
// ➢ Dividir un fichero en función de:
// • De un número n de caracteres (en cada fichero generado debe poseer n
// caracteres).
// • De un número l de líneas (en cada fichero generado debe poseer l líneas).
// ➢ Unir ficheros: dada una lista de ficheros generará un nuevo fichero que
// resultara de la unión de los anteriores.
