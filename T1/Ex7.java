
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ex7 {

    /*7. Escribir un método que reciba dos parámetros: el primero será el nombre de un
archivo y el segundo una operación al realizar (para ordenar se puede usar la
clase Collections) siendo estas:
? Opción n: contar el número de líneas y palabras del archivo.
? Opción A: crear un nuevo archivo con las líneas del archivo inicial ordenadas
forma ascendente siendo esta ordenación sensible al caso.
? Opción D: crear un nuevo archivo con las líneas del archivo inicial ordenadas
forma descendente siendo esta ordenación sensible al caso.
? Opción a: crear un nuevo archivo con las líneas del archivo inicial ordenadas
forma ascendente siendo la ordenación no sensible al caso.
? Opción d: crear un nuevo archivo con las líneas del archivo inicial ordenadas
forma descendente siendo la ordenación no sensible al caso. */
    public static void showMenu(String fileName, char option) {
        switch (option) {
            case 'n' ->
                System.out.println();
            case 'A' ->
                System.out.println();
            case 'D' ->
                System.out.println();
            case 'a' ->
                System.out.println();
            case 'd' ->
                System.out.println();
            default ->
                throw new AssertionError();
        }
    }

    public static int countNumberLines(String fileName) {
        int counterTotal = 0;
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNext()) {
                counterTotal++;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return counterTotal;
    }

    public static int countNumberWords(String fileName) {
        int total = 0;
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNext()) {
                String[] totalWords = sc.nextLine().split("\\W+");
                total += totalWords.length;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return total;
    }

    public static String fileText(String fileName) throws FileNotFoundException{
        String fileText = "";
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNext()) {
                fileText += sc.nextLine() + "\n";
            }
        }
        return fileText;
    }

    public static void createFile(String fileName) throws IOException{
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(fileText("archivo.txt"));
        }
    }

    @SuppressWarnings("ManualArrayToCollectionCopy")
    public static ArrayList<String> convertArrayFromArrayList(String[] arrayToConvert){
        ArrayList<String> arrayListToAddElements = new ArrayList<>();
        for (String string : arrayToConvert) {
            arrayListToAddElements.add(string);
        }
        return arrayListToAddElements;
    }

    public static void sortText(ArrayList<String> fileText, boolean isAscendant,boolean isSensitive){
        if (!isSensitive && !isAscendant) {
            Collections.sort(fileText, String.CASE_INSENSITIVE_ORDER);
        } else if (isAscendant && isSensitive) {
            Collections.sort(fileText);
        } else if (!isAscendant && isSensitive){
            Collections.sort(fileText, Collections.reverseOrder());
        } else {
            Collections.sort(fileText, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        }
    }

    public static void main(String[] args) throws IOException {
        showMenu("", 'A');
    }
}
