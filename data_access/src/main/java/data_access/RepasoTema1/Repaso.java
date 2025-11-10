package data_access.RepasoTema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
    public static void createFiles(int numberFilesToCreate, String fileName, String folderPath) throws IOException {
        File newFile;
        for (int i = 0; i < numberFilesToCreate; i++) {
            newFile = new File(String.format("%s%d.txt", fileName, i));
            try (FileWriter fw = new FileWriter(newFile)) {
                fw.write(String.format("Este es el fichero %s%d.txt", fileName, i));
            }
        }
    }

    // 3. Crea un método que permita buscar palabras en un fichero de texto. Se debe
    // mostrar el
    // número de ocurrencias de dicha palabra. Utiliza un buffer para la lectura.
    public static void searchWords(String pathFile, String wordToSearch) throws FileNotFoundException {
        int counter = 0;
        try (Scanner sc = new Scanner(new File(pathFile))) {
            String[] buffer;
            while (sc.hasNextLine()) {
                buffer = sc.nextLine().split(" ");
                for (String word : buffer) {
                    if (word.equals(wordToSearch)) {
                        counter++;
                    }
                }
            }
        } catch (Exception e) {
        }
        System.out.println("La palabra (" + wordToSearch + ") aparece " + counter + " veces");
    }

    // 4. Crea un método que permita eliminar todas las ocurrencias de una palabra
    // dada en un
    // fichero de texto. Este código producirá automáticamente un fichero con la
    // siguiente
    // nomenclatura: Si el fichero de entrada se llama fichero.txt, el fichero
    // generado se
    // llamará fichero_2.txt.
    // Pista: nextLine.replaceAll(Lo que quieres borrar, Lo que quiere agregar)
    public static void removeWordFromFile(String pathFile, String wordToRemove){
        try (Scanner sc = new Scanner(new File(pathFile))) {
            String[] buffer;
            while (sc.hasNextLine()) {
                buffer = sc.nextLine().split(" ");
                for (String word : buffer) {
                    if (word.equals(wordToRemove)) {
                        
                    }
                }
            }
        } catch (Exception e) {
        }
    }


    public static void main(String[] args) {
        try {
            // createFiles(5, "archivo",
            // "data_access\\src\\main\\java\\data_access\\RepasoTema1\\Repaso.java");
            searchWords("archivo0.txt", "el");
        } catch (IOException ex) {
        }
    }
}
