
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ex2 {
    static String searchLine(String fileName, String wordToCompare){
        int counterLines = 0;
        String numberOfLineIsFind = "";
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNext()) { 
                if(sc.nextLine().contains(wordToCompare)){
                    counterLines++;
                    numberOfLineIsFind += counterLines + " ";
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        return String.format("Numero veces: %d, Lineas: %s", counterLines, numberOfLineIsFind);
    }

    static String searchLineAumented(String fileName, String wordToCompare){
        int counterLines = 0;
        String numberOfLineIsFind = "";
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNext()) {
                String[] textToCompare = sc.nextLine().split(" ");
                for (String string : textToCompare) {
                    if(string.equals(wordToCompare)){
                        counterLines++;
                        numberOfLineIsFind += counterLines + " ";
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        return String.format("Numero veces: %d, Lineas: %s", counterLines, numberOfLineIsFind);
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Introduce el nombre del archivo con su extensión: ");
            String fileName = sc.nextLine();
            System.out.print("Introduce la palabra a buscar en el archivo: ");
            String wordToCompare = sc.nextLine();
            System.out.println(searchLineAumented(fileName,wordToCompare));
        }
    }
}
