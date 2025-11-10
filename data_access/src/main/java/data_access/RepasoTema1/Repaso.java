package data_access.RepasoTema1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Repaso {
    // 1. Crea un método que reciba una carpeta y liste el contenido de dicha
    // carpeta de aquellos
    // archivos cuya extensión sea “.txt”.
    public static void listContentFolder(String folderName, String extensionToSearch) {
        // try () {
        // } catch (Exception e) {
        // }
    }

    // 2. Crea un método que debe crear n archivos, nombre(1).txt, nombre(2).txt, ….
    // nombre(n).txt en la carpeta que se solicita al usuario. Dentro de cada
    // archivo debe
    // escribirse la frase: “Este es el fichero nombre(n).txt”.
    public static void createFiles(int numberFilesToCreate, String fileName, String folderPath) throws IOException{
        File newFile;
        for (int i = 0; i < numberFilesToCreate; i++) {
            newFile = new File(String.format("%s%d.txt", fileName, i));
            try (FileWriter fw = new FileWriter(newFile)) {
                fw.write(String.format("Este es el fichero %s%d.txt", fileName, i));
            }
        }
    }

    public static void main(String[] args) {
        try {
            createFiles(5, "archivo", "data_access\\src\\main\\java\\data_access\\RepasoTema1\\Repaso.java");
        } catch (IOException ex) {
        }
    }
}
