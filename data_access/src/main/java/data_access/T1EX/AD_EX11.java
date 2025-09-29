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
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AD_EX11 {
    // 11. Compara la eficiencia de copiar un fichero de 100MB usando por un lado
    // BufferedInputStream y BufferedOutputStream y por otro FileInputStream y
    // FileOutputStream. En este último caso prueba con los siguientes tamaños de
    // buffer: 10,100,1000, …

    public static void escribirArchivoConBuffered() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("prueba100MB.txt");
                BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                    while (true) { 
                        // bos.write();
                    }
        } catch (EOFException e) {
        }
    }

    public static ArrayList<String> leerArchivoConBuffered() throws IOException {
        ArrayList<String> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("prueba100MB.txt");
                BufferedInputStream bis = new BufferedInputStream(fis)) {
                    int i;
                    while ((i = bis.read()) != -1) { 
                        System.out.print((char)i);
                    }
        } catch (EOFException e) {
        }
        return data;
    }

    public static void main(String[] args) throws IOException {
        // long principio = System.currentTimeMillis();
        // try (FileInputStream fis = new FileInputStream(new File("prueba100MB.txt"));
        //         BufferedInputStream bis = new BufferedInputStream(fis)) {
        //             bis.read();
        // } catch (Exception e) {
        // }
        // for (int i = 0; i < 100000; i++) {
        //     System.out.println(i);
        // }
        // long finaal = System.currentTimeMillis();
        // System.out.println(finaal - principio + "ms");

        leerArchivoConBuffered();
    }
}
