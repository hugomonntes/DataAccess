
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderEx {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        try (FileReader fr = new FileReader("T1\\Teoría.md")) {
            int i;
            while ((i = fr.read()) != -1) {
                System.out.print((char) i);
            }
        }
    }
}
