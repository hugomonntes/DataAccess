package data_access.T1EX;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class RepasoPrimitivos {
    public static void leerArchivo(String path){
        try (DataInputStream dis = new DataInputStream(new FileInputStream(path))) {
            while (true) {
                int a = dis.readInt();
                System.out.println(a);
            }
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) {
        leerArchivo("notas.dat");
    }
}
