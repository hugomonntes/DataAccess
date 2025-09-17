
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class FileInputStreamEx {

    public static void main(String[] args) {
        try (FileInputStream fin = new FileInputStream("archivo.txt"); 
            FileOutputStream fout = new FileOutputStream("fichero.dat")) {
                int i;
                while ((i = fin.read()) != 1 ) { 
                    fout.write((char) i);
                }
        } catch (Exception e) {
        }
    }
}
