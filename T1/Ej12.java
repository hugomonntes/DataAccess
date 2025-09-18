
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Ej12 {

    public static void main(String[] args) {
        // ESCRIBIR OBJETOS
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Denis", 5, 100.0F));
        ArrayList<Persona> personas;
        personas = new ArrayList<>();
        personas.add(new Persona("Lugonpa", 21));
        try (FileOutputStream fichero = (new FileOutputStream("clientes.dat"));) {

        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
