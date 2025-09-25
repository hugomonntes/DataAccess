
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AD_EX9 {

    //     Realiza un programa que permita almacenar, de forma binaria, los datos de unos
    // alumnos en el fichero alumnos.dat (guarda los datos de forma individual, no
    // como un objeto). Los datos de cada alumno serán: código como entero, nombre
    // como cadena de texto, altura como un número con dos decimales. El programa
    // debe realizar las siguientes tareas:
    // ➢ Dar de alta nuevos alumnos.
    // ➢ Consultar alumnos.
    // ➢ Modificar alumnos.
    // ➢ Borrar alumnos.
    // El programa deberá avisar de posibles problemas encontrados como puede ser el
    // intentar borrar un alumno que no exista.
    public static void escribirArchivoBinario(String fileName, ArrayList<Alumno> alumnos) {
        try (FileOutputStream fos = new FileOutputStream(new File(fileName), true); DataOutputStream out = new DataOutputStream(fos)) {
            for (Alumno alumno : alumnos) {
                out.writeInt(alumno.getCodigo());
                out.writeUTF(alumno.getNombre());
                out.writeFloat(alumno.getAltura());
            }
        } catch (Exception e) {
        }
    }

    public static void consultarArchivoBinario(String fileName) throws FileNotFoundException, IOException {
        try (FileInputStream fis = new FileInputStream(new File(fileName)); DataInputStream in = new DataInputStream(fis)) {
            while (true) {
                System.out.printf("%d %s %.2f%n", in.readInt(), in.readUTF(), in.readFloat());
            }
        } catch (EOFException e) {
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno(1, "Juan", 1.75f));
        alumnos.add(new Alumno(2, "Ana", 1.65f));
        escribirArchivoBinario("alumnos.dat", alumnos);
        consultarArchivoBinario("alumnos.dat");
    }
}
