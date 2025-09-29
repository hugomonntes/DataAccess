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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AD_EX11 {
    // 11. Compara la eficiencia de copiar un fichero de 100MB usando por un lado
    // BufferedInputStream y BufferedOutputStream y por otro FileInputStream y
    // FileOutputStream. En este último caso prueba con los siguientes tamaños de
    // buffer: 10,100,1000, …

    public static void pruebaBufferStream() throws IOException{
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream("prueba100MB.txt"))){
            try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("prueba100MB2.txt"))){
                while ((bis.read()) != -1) {
                    bos.write(bis.read());
                }
            }
        }
    }
    public static void pruebaFileStream() throws IOException{
        try(FileInputStream fis = new FileInputStream("prueba100MB.txt")){
            try(FileOutputStream fos = new FileOutputStream("prueba100MB3.txt")){
                while (fis.read() != -1) {
                    fos.write(fis.read());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        long tiempoI = System.nanoTime();
        pruebaBufferStream();
        long tiempoF = System.nanoTime();
        System.out.println("Tiempo BufferedInputStream/ BufferedOutputStream: " + (tiempoF - tiempoI)/1000000 + "n/s");
        long tiempoInicio = System.nanoTime();
        pruebaFileStream();
        long tiempoFinal = System.nanoTime();
        System.out.println("Tiempo FileInputStream/FileOutputStream: " + (tiempoFinal - tiempoInicio)/1000000 + "n/s");

    }
}
