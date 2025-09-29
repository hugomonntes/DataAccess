/*-
 * =====LICENSE-START=====
 * Java 11 Application
 * ------
 * Copyright (C) 2020 - 2025 Organization Name
 * ------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====LICENSE-END=====
 */
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
