import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Libreria {
    public static String leerArchivosBinarios(String fileName){
        String dataStorage = "";
        try (FileInputStream fin = new FileInputStream(fileName)) {
                int i;
                while ((i = fin.read()) != 1 ) { 
                    dataStorage += (char) i;
                }
        } catch (Exception e) {
        }
        return dataStorage;
    }

    public static void escribirArchivoBinario(String fileName){
        try (FileInputStream fin = new FileInputStream(fileName); 
            FileOutputStream fout = new FileOutputStream("b.dat")) {
                int i;
                while ((i = fin.read()) != 1 ) { 
                    fout.write((char) i);
                }
        } catch (Exception e) {
        }
    }
}
