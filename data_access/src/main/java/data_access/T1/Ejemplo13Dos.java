package data_access.T1;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Ejemplo13Dos {
    public static void main(String[] args) throws ClassNotFoundException {
        try (FileInputStream fin = new FileInputStream("clientes.dat"); ObjectInputStream ois = new ObjectInputStream(fin) {
        }) {
            Persona persona;
            while (true) {
                Object obj = ois.readObject();
                if (obj.getClass() == Persona.class) {
                    persona = (Persona) obj;
                }
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}

