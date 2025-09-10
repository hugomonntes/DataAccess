
import java.io.File;

public class ejemplo1 {

    public static void main(String[] args) {
        File newFile = new File("Hola");
        // System.out.println(newFile.getName());
        // System.out.println(newFile.getPath());
        // System.out.println(newFile.exists());
        // newFile.mkdir();
        // newFile.renameTo(new File("Holaaaa"));
        File[] arrayArchivos = newFile.listFiles();
        for (int i = 0; i < arrayArchivos.length; i++) {
            System.out.println(arrayArchivos[i].getName());
        }
    }
}
