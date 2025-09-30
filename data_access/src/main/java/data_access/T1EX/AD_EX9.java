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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AD_EX9 {

    //     Realiza un programa que permita almacenar, de forma binaria, los datos de unos
    // alumnos en el fichero alumnos.dat (guarda los datos de forma individual, no
    // como un objeto). Los datos de cada alumno serán: código como entero, nombre
    // como cadena de texto, altura como un número con dos decimales. El programa
    // debe realizar las siguientes tareas:
    // ➢ Dar de alta nuevos alumnos.
    // ➢ Consultar alumnos.
    // ➢ Modificar alumnos.
    // ➢ Borrar alumnos.
    // El programa deberá avisar de posibles problemas encontrados como puede ser el
    // intentar borrar un alumno que no exista.

    public static void escribirArchivoBinario(String fileName, ArrayList<Alumno> alumnos) {
        try (FileOutputStream fos = new FileOutputStream(new File(fileName), false); DataOutputStream out = new DataOutputStream(fos)) {
            for (Alumno alumno : alumnos) {
                out.writeInt(alumno.getCodigo());
                out.writeUTF(alumno.getNombre());
                out.writeFloat(alumno.getAltura());
            }
        } catch (Exception e) {
        }
    }

    public static ArrayList<Alumno> consultarArchivoBinario(String fileName) throws FileNotFoundException, IOException {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(fileName)); DataInputStream in = new DataInputStream(fis)) {
            while (true) {
                alumnos.add(new Alumno(in.readInt(), in.readUTF(), in.readFloat()));
            }
        } catch (EOFException e) {
        }
        return alumnos;
    }

    public static void modificarAlumno(ArrayList<Alumno> alumnos, int queryCode, String nombre, float altura){
        for (Alumno alumno : alumnos) {
            if (alumno.codigo == queryCode) {
                alumno.nombre = nombre;
                alumno.altura = altura;
            }
        }
    }

    public static Alumno crearAlumno(int codigo, String nombre, float altura){
        return new Alumno(codigo, nombre, altura);
    }

    public static void añadirAlumno(ArrayList<Alumno> alumnos, Alumno alumno){
        if (alumnos.indexOf(alumno) != -1) { 
            throw new IllegalArgumentException();
        }
        alumnos.add(alumno);
    }

    public static void borrarAlumno(ArrayList<Alumno> alumnos, int codigo){
        for (int i = 0; i < alumnos.size(); i++) {
            if (alumnos.get(i).codigo == codigo) {
                alumnos.remove(i);
                i--;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        try (Scanner sc = new Scanner(System.in)) {
            int option;
            String fileName = "alumnos.dat";
            ArrayList<Alumno> alumnos = consultarArchivoBinario(fileName);
            do {
                System.out.println("1.- Añadir");
                System.out.println("2.- Consultar");
                System.out.println("3.- Modificar");
                System.out.println("4.- Borrar");
                System.out.println("5.- Salir.");
                option = sc.nextInt();
                switch (option) {
                    case 1:
                        int numRamdon = (int)(Math.random()*50);
                        añadirAlumno(alumnos, crearAlumno(51, "Alumno" + numRamdon, 1.85f));
                        escribirArchivoBinario(fileName, alumnos);
                        ;
                            break;
                    case 2:
                        for (Alumno alumno : alumnos) {
                            System.out.println(alumno);
                        }
                        ;
                            break;
                    case 3:
                        modificarAlumno(alumnos, 1, "Jaime", 1.9f);
                        escribirArchivoBinario(fileName, alumnos)
                        ;
                            break;
                    case 4:
                        borrarAlumno(alumnos, 1);
                        escribirArchivoBinario(fileName, alumnos)
                        ;
                            break;
                    case 5:
                        ;
                            break;
                    default:
                        
                            break;
                }
            
            } while (option!=5);
        }
    }
}
