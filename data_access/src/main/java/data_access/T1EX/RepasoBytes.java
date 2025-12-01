package data_access.T1EX;

import java.io.File;
import java.io.FileInputStream;

public class RepasoBytes {
    public static void leerArchivo(String path){
        try (FileInputStream fis = new FileInputStream(new File(path))) {
            int i;
            while ((i = fis.read()) != -1) {
                System.out.print((char)i);
            }
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) {
        leerArchivo("notas.dat");
    }
}
