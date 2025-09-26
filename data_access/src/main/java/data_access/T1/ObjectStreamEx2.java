package data_access.T1;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class ObjectStreamEx2 {

    public static void main(String[] args) {
        // Escribir obj
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("null", 2, 45.6f));
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("listaClientes.dat"))) {
            for (Cliente cliente : clientes) {
                out.writeObject(cliente);
            }
        } catch (IOException e) {
        }

        // Leer Obj
        // try (ObjectInputStream oin = new ObjectInputStream()) {
        // } catch (Exception e) {
        //     // TODO: handle exception
        // }
        
        @SuppressWarnings("unused")
        class AppendingObjectOutputStream extends ObjectOutputStream {
            public AppendingObjectOutputStream(OutputStream out) throws IOException {
                super(out);
            }

            @Override
            protected void writeStreamHeader() throws IOException {
                reset();// do not write a header
            }
        }
    }
}
