
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerEx {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("archivo.txt"))) {
            while(sc.hasNext()){
                System.out.println(sc.next());
            }
        } catch (FileNotFoundException e) {

        }
    }
}
