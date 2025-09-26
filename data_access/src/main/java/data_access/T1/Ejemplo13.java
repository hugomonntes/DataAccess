package data_access.T1;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Ejemplo13 {
    public static void main(String[] args) throws ClassNotFoundException {
        try (FileInputStream fin = new FileInputStream("clientes.dat"); ObjectInputStream ois = new ObjectInputStream(fin) {
        }) {
            Persona persona;
            while (true) {
                persona = (Persona) ois.readObject();
                System.out.println(persona.getNombre());
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
