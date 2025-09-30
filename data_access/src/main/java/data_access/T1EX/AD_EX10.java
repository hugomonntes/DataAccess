package data_access.T1EX;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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

    public static void escribirArchivo(File fichero, ArrayList<Object> objetos) {
        try (FileOutputStream fos = new FileOutputStream(fichero);
                ObjectOutputStream out = new ObjectOutputStream(fos)) {
            for (Object obj : objetos) {
                out.writeObject(obj);
            }
        } catch (Exception e) {
        }
    }

    public static ArrayList<Object> consultarArchivo(File fichero) {
        ArrayList<Object> objetos = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fichero); ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                objetos.add(ois.readObject());
            }
        } catch (Exception e) {
        }
        return objetos;
    }

    public static void eliminarObjeto(ArrayList<Object> objetos, Object obj) {
        for (int i = 0; i < objetos.size(); i++) {
            if (obj instanceof Persona && objetos.get(i) instanceof Persona) {
                if (((Persona) obj).getNombre().equals(((Persona) objetos.get(i)).getNombre())) {
                    objetos.remove(i);
                    i--;
                }
            } else if (obj instanceof Depart && objetos.get(i) instanceof Depart) {
                if (((Depart) obj).getNumeroDepart() == ((Depart) objetos.get(i)).getNumeroDepart()) {
                    objetos.remove(i);
                    i--;
                }
            }
        }
    }

    public static void main(String[] args) {
        File fichero = new File("objetos.dat");
        ArrayList<Object> objetos = consultarArchivo(fichero);

        añadirObjetos(objetos, crearPersona("Diego", 334));
        añadirObjetos(objetos, crearPersona("Hgo", 334));
        añadirObjetos(objetos, crearPersona("JJJ", 334));

        añadirObjetos(objetos, crearDepartamento("Ventas", 101));
        añadirObjetos(objetos, crearDepartamento("Movil", 1011));


        escribirArchivo(fichero, objetos);

        System.out.println("Antes de borrar");
        for (Object obj : objetos) {
            System.out.println(obj.toString());
        }

        eliminarObjeto(objetos, new Persona("Jaime", 33));
        eliminarObjeto(objetos, new Depart("Ventas", 101));

        escribirArchivo(fichero, objetos);

        System.out.println("Después de borrar");
        for (Object obj : objetos) {
            System.out.println(obj.toString());
        }
    }

}
