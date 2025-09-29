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
package data_access.T1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ex2 {
    static String searchLine(String fileName, String wordToCompare){
        int counterLines = 0;
        String numberOfLineIsFind = "";
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNext()) { 
                if(sc.nextLine().contains(wordToCompare)){
                    counterLines++;
                    numberOfLineIsFind += counterLines + " ";
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        return String.format("Numero veces: %d, Lineas: %s", counterLines, numberOfLineIsFind);
    }

    static String searchLineAumented(String fileName, String wordToCompare){
        int counterLines = 0;
        String numberOfLineIsFind = "";
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNext()) {
                String[] textToCompare = sc.nextLine().split("\\W+");
                for (String word : textToCompare) {
                    if(word.equalsIgnoreCase(wordToCompare)){
                        counterLines++;
                        numberOfLineIsFind += counterLines + " ";
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        return String.format("Numero veces: %d, Lineas: %s", counterLines, numberOfLineIsFind);
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Introduce el nombre del archivo con su extensión: ");
            String fileName = sc.nextLine();
            System.out.print("Introduce la palabra a buscar en el archivo: ");
            String wordToCompare = sc.nextLine();
            System.out.println(searchLine(fileName,wordToCompare));
        }

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Introduce el nombre del archivo con su extensión: ");
            String fileName = sc.nextLine();
            System.out.print("Introduce la palabra a buscar en el archivo: ");
            String wordToCompare = sc.nextLine();
            System.out.println(searchLineAumented(fileName,wordToCompare));
        }
    }
}
