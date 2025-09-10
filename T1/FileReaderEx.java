
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
            System.out.println();
        }

        try (FileReader fr = new FileReader("T1\\Teoría.md")) {
            char[] buffer = new char[4];
            int i = 0;
            for (int j = 0; j < 0 || (i = fr.read()) != -1; j++) {
                buffer[j] = (char) i;
            }

            for (char c : buffer) {
                System.out.print(c);
            }
        }
    }
}
