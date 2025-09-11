
import java.io.FileWriter;
import java.io.IOException;

public class FileWritterEx {
    public static void main(String[] args) {
        String resultado = "Celta 2 - 0 Girona \n";
        try (FileWriter fw = new FileWriter("archivo.txt", true)){
            fw.write(resultado);
        } catch (IOException e) {
        }
    }
}
