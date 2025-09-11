
import java.io.File;
import java.util.Scanner;

public class Ex2 {
    static String searchLine(String wordToCompare){
        int counterLines = 0;
        String numberOfLineIsFind = "";
        try (Scanner sc = new Scanner(new File("archivo.txt"))) {
            while (sc.hasNext()) { 
                if(sc.nextLine().contains(wordToCompare)){
                    counterLines++;
                    numberOfLineIsFind += counterLines + " ";
                }
            }
        } catch (Exception e) {
        }
        return String.format("Numero veces: %d, Lineas: %s", counterLines, numberOfLineIsFind);
    }
    public static void main(String[] args) {
        System.out.println(searchLine("Celta"));
    }
}
