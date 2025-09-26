package data_access.T1;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ObjectStreamEx {

    public static void main(String[] args) {
        // try (DataOutputStream dout = new DataOutputStream(new FileOutputStream("listaClientes.dat"))) {
        //     ArrayList<Cliente> listaClientes = new ArrayList<>();
        //     listaClientes.add(new Cliente("aaaa", 2, 123.3f));
        //     for (Cliente cliente : listaClientes) {
        //         dout.writeUTF(cliente.getNombre());
        //         dout.writeInt(cliente.getNumCompras());
        //         dout.writeFloat(cliente.getCredito());
        //     }
        // } catch (Exception e) {
        // }

        // //Leer archivo en binario
        // try (DataInputStream din = new DataInputStream(new FileInputStream("listaClientes.dat"))) {
        //     while (true) {
        //         System.out.println("Nombre: " + din.readUTF() + " Num: " + din.readInt() + " Credito: " + din.readFloat());
        //     }
        // } catch (Exception e) {
        // }


        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Iago", 5, 2));
        clientes.add(new Cliente("Hugo", 5, 2));
        clientes.add(new Cliente("Jaime", 5, 2));
        clientes.add(new Cliente("AAAA", 5, 2));
        clientes.add(new Cliente("BBBB", 5, 2));
        clientes.add(new Cliente("CCCC", 5, 2));
        
        try (DataOutputStream dout = new DataOutputStream(new FileOutputStream("listaClientes.dat"))) {
            for (Cliente cliente : clientes) {
                dout.writeUTF(cliente.getNombre());
                dout.writeInt(cliente.getNumCompras());
                dout.writeFloat(cliente.getCredito());
            }
        } catch (Exception e) {
        }

        //Leer archivo en binario
        try (DataInputStream din = new DataInputStream(new FileInputStream("listaClientes.dat"))) {
            while (true) {
                System.out.println("Nombre: " + din.readUTF() + " Num: " + din.readInt() + " Credito: " + din.readFloat());
            }
        } catch (Exception e) {
        }
    }
}
