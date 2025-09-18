
import java.io.File;
import java.util.ArrayList;

public class Ej12 {
    public static void escribirObjetos(File fichero, ArrayList<Cliente> clientes){
        
    }

    public static void main(String[] args) {
        // ESCRIBIR OBJETOS
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Denis", 5, 100.0F));
        ArrayList<Persona> personas;
        personas = new ArrayList<>();
        personas.add(new Persona("Lugonpa", 21));
        File fichero = new File("clientes.dat");
        
    }
}
