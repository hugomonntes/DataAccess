package data_access.RepasoTema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
    public static void removeWordFromFile(String pathFileToRemove, String fileNameToCreate, String wordToRemove)
            throws FileNotFoundException {
        ArrayList<String> buffer = new ArrayList<>();
        try (PrintWriter pw = new PrintWriter(new File(fileNameToCreate))) {
            try (Scanner sc = new Scanner(new File(pathFileToRemove))) {
                while (sc.hasNextLine()) {
                    buffer.add(sc.nextLine().replaceAll(wordToRemove, ""));
                }
                for (String word : buffer) {
                    pw.write(word + "\n");
                }
            } catch (Exception e) {
            }
        }
    }

    // Crea un método que encripta y otro que desencripta el contenido de un fichero
    // de texto
    // utilizando el código César. El cifrado César es un tipo de cifrado de
    // sustitución en el que
    // cada letra en el texto se desplaza un cierto número de lugares en el
    // alfabeto. Por
    // ejemplo, con un desplazamiento de 2, “A” se reemplazaría por “C”, “B” se
    // convertiría en
    // “D”. Con desplazamiento 5, “C” se reemplazaría por “H”, “E” se convertiría en
    // “J”, etc.
    // Se quiere que el usuario introduzca por consola este número de
    // desplazamiento.
    public static String cesarCode(String wordToEncript, int numEncript) {
        String wordLower = wordToEncript.toLowerCase().trim();
        String newCadena = "";
        for (int i = 0; i < wordLower.length(); i++) {
            int ascii = (int) wordLower.charAt(i);
            int asciiSumado = ascii + numEncript;
            newCadena += wordLower.replace(wordLower.charAt(i), (char) asciiSumado).charAt(i);
        }
        return newCadena;
    }

    public static String desEncript(String wordToDesEncript, int numEncript){
        for (int i = 0; i < wordToDesEncript.length(); i++) {
            int ascii = (int) wordToDesEncript.charAt(i);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // createFiles(5, "archivo",
        // "data_access\\src\\main\\java\\data_access\\RepasoTema1\\Repaso.java");
        // searchWords("archivo0.txt", "el");
        // removeWordFromFile("archivo0.txt", "archivo1.txt", "BBB");
        System.out.println(cesarCode("abc", 254));
    }
}
