
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWiterEx {
    public static void main(String[] args) {
        try (PrintWriter pw = new PrintWriter("archivo2.txt")) {
            pw.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
        } catch (IOException e) {
        }
    }
}
