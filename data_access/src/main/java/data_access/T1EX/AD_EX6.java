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
import java.util.Scanner;

public class AD_EX6 {
    public static void main(String[] args) throws IOException {
        //createFile(splitInFiles(storageDataFile("a.txt"), calculateNumFiles(storageDataFile("a.txt"), 5), 5));
        createFile1(storageDataFileInLines("a.txt"));
    }
    //#region
    static String storageDataFile (String fileName) throws FileNotFoundException {
        String dataStorage = "";
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNext()) { 
                dataStorage += sc.nextLine();
            }
        }
        return dataStorage;
    }

    static int calculateNumFiles(String dataFile, int maxChars){
        return dataFile.length() / maxChars;
    }

    static String[] splitInFiles(String dataFile, int numFiles, int maxChars){
        String[] parts = new String[numFiles];
        int start = 0;

        for (int i = 0; i < numFiles; i++) {
            int end = Math.min(start + maxChars, dataFile.length());
            parts[i] = dataFile.substring(start, end);
            start = end;
            if ((end - start) < maxChars) { // FIXME
                start += (maxChars - (start + end));
            }
        }

        return parts;
    }

    static void createFile(String[] dataFiles) throws IOException{
        for (int i = 0; i < dataFiles.length; i++) {
            try (FileWriter fw = new FileWriter(String.format("file%d.txt", i))) {
                fw.write(dataFiles[i]);
            } catch (Exception e) {
            }
        }
    }
    //#endregion

    static String[] storageDataFileInLines (String fileName) throws FileNotFoundException {
        String dataStorage[] = new String[]{};
        try (Scanner sc = new Scanner(new File(fileName))) {
            int i = 0;
            while (sc.hasNext()) { 
                dataStorage[i] = sc.nextLine();
                i++;
            }
        }
        return dataStorage;
    }

    static void createFile1(String[] dataFiles) throws IOException{
        for (int i = 0; i < dataFiles.length; i++) {
            try (FileWriter fw = new FileWriter(String.format("file%d.txt", i))) {
                fw.write(dataFiles[i]);
                System.out.println(dataFiles[i]);
            } catch (Exception e) {
            }
        }
    }
}

// 6. Crea un programa que realice las siguientes acciones:
// ➢ Dividir un fichero en función de:
// • De un número n de caracteres (en cada fichero generado debe poseer n
// caracteres).
// • De un número l de líneas (en cada fichero generado debe poseer l líneas).
// ➢ Unir ficheros: dada una lista de ficheros generará un nuevo fichero que
// resultara de la unión de los anteriores.
