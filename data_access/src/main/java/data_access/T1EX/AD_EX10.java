package data_access.T1EX;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import data_access.T1.Cliente;

public class AD_EX10 {
    // 10.Realiza un programa que defina las clases Persona y Depart almacenado
    // estas
    // clases como objetos en un fichero. Debes crear los atributos y métodos que
    // creas
    // necesarios.
    // Deberá permitir:
    // ➢ La inclusión de nuevas personas y departamentos.
    // ➢ Su consulta.
    // ➢ El borrado de persona o departamento.

    public static Depart crearDepartamento(String nombreDepart, int numeroDepart) {
        return new Depart(nombreDepart, numeroDepart);
    }

    public static Persona crearPersona(String nombre, int edad) {
        return new Persona(nombre, edad);
    }

    public static void añadirObjetos(ArrayList<Object> objetos, Object obj) {
        if (obj.getClass() == Persona.class || obj.getClass() == Depart.class) {
            objetos.add(obj);
        }
    }

    public static void escribirArchivo(File fichero, ArrayList<Object> objetos){
        try (FileOutputStream fos = new FileOutputStream(fichero); ObjectOutputStream out = new ObjectOutputStream(fos)) {
            for (Object obj : objetos) {
                out.writeObject(obj);
            }
        } catch (Exception e) {
        }
    }

    public static ArrayList<Object> consultarArchivo(File fichero){
        ArrayList<Object> objetos = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fichero); ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                objetos.add(ois.readObject());
            }
        } catch (Exception e) {
        }
        return objetos;
    }

    public static void main(String[] args) {
        ArrayList<Object> objetos = consultarArchivo(new File("objetos.dat"));
        añadirObjetos(objetos, crearPersona("Fuiin", 33));
        escribirArchivo(new File("objetos.dat"), objetos);
        for (Object object : objetos) {
            System.out.println(object);
        }
    }

}
