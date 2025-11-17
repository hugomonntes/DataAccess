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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class AD_EX4 {
    // @SuppressWarnings({"BoxedValueEquality", "NumberEquality"})
    // public static void calcularCharMasRepetido(ArrayList<Character> chars){
    //     char masRepetido = ' ';
    //     int maxRepeticiones = 0;

    //     for (Character character : chars) {
    //         int contador = 0;
    //         for (int i = 0; i < chars.size(); i++) {
    //             if (character == chars.get(i)) {
    //                 contador++;
    //             }
    //         }
    //         if (contador > maxRepeticiones) {
    //             maxRepeticiones = contador;
    //             masRepetido = character;
    //         }
    //     }
    //     System.out.println("Carácter más repetido: " + masRepetido + " con " + maxRepeticiones + " repeticiones");
    // }

    public static ArrayList<Character> leerArchivo(String nombreArchivo) {
        ArrayList<Character> caracteres = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nombreArchivo))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                for (int i = 0; i < linea.length(); i++) {
                    caracteres.add(linea.charAt(i));
                }
            }
        } catch (Exception e) {
        }
        return caracteres;
    }

    public static void calcularCharMasRepetido(ArrayList<Character> characters){
        int counterNumeroApariciones = 0;
        int counterAux = 0;
        char charMasAparecido = 'a';
        for (int i = 0; i < characters.size(); i++) { // a
            for (int j = 0; j < characters.size(); j++) { // a b c a d e a
                if(characters.get(i) == characters.get(j)){
                    counterNumeroApariciones++;
                }
            }
            if(counterNumeroApariciones > counterAux){
                counterAux = counterNumeroApariciones;
                charMasAparecido = characters.get(i);
            }
            counterNumeroApariciones = 0;
        }
        System.out.println("Char mas aparecido " + charMasAparecido + " con " + counterAux + " apariciones");
    }

    public static void main(String[] args) {
        calcularCharMasRepetido(leerArchivo("a.txt"));
    }
}
