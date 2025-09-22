
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AD_EX6 {
    public static void main(String[] args) throws IOException {
        int maxCharsPerFile = 5;
        createFile(splitFiles(storageDataFile("a.txt"), calculateNumFiles(storageDataFile("a.txt"), maxCharsPerFile), maxCharsPerFile));
    }

    @SuppressWarnings("unused")
    static String storageDataFile (String fileName) throws FileNotFoundException {
        String dataStorage = "";
        Scanner sc = new Scanner(new File(fileName));
        while (sc.hasNext()) { 
            dataStorage += sc.nextLine();
        }
        return dataStorage;
    }

    @SuppressWarnings("unused")
    static int calculateNumFiles(String dataFile, int maxChars){
        return dataFile.length() / maxChars;
    }

    @SuppressWarnings("unused")
    static String[] splitFiles(String dataFile, int numFiles, int maxChars){
        return dataFile.splitWithDelimiters("",maxChars);
    }

    static void createFile(String[] dataFiles) throws IOException{
        for (int i = 0; i < dataFiles.length; i++) {
            try (FileWriter fw = new FileWriter(String.format("file%d.txt", i))) {
                fw.write(dataFiles[i]);
            } catch (Exception e) {
            }
        }
    }

    
}

// 6. Crea un programa que realice las siguientes acciones:
// ➢ Dividir un fichero en función de:
// • De un número n de caracteres (en cada fichero generado debe poseer n
// caracteres).
// • De un número l de líneas (en cada fichero generado debe poseer l líneas).
// ➢ Unir ficheros: dada una lista de ficheros generará un nuevo fichero que
// resultara de la unión de los anteriores.
