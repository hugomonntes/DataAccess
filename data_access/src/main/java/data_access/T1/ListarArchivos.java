package data_access.T1;


import java.io.File;

public class ListarArchivos {

    public static void main(String[] args) {
        File tema = new File("C:\\Users\\Hugo Montes\\Documents\\DataAccess\\T1");
        for (File doc : tema.listFiles()) {
            if (doc.isDirectory()) {
                System.out.println(doc.getName() + " es un directorio");
            }
        }
    }
}
