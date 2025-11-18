package data_access.T1EX;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RepasoObjects {
    public static Producto crearProducto(String nombre, int codigo, double precio) {
        return new Producto(nombre, codigo, precio);
    }

    public static ArrayList<Producto> añadirProducto(ArrayList<Producto> productos, Producto producto) {
        productos.add(producto);
        return productos;
    }

    public static ArrayList<Producto> eliminarProducto(ArrayList<Producto> productos, int codigo) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == codigo) {
                productos.remove(i);
                i--;
            }
        }
        return productos;
    }

    public static void mostrarProductos(ArrayList<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }
    }

    public static ArrayList<Producto> cargarEnArchivo(String nombreArchivo, ArrayList<Producto> productos)
            throws ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(new File(nombreArchivo));
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                productos.add((Producto) ois.readObject());
            }
        } catch (IOException e) {
        }
        return productos;
    }

    public static void guardarEnArchivo(String nombreArchivo, ArrayList<Producto> productos) {
        try (FileOutputStream fos = new FileOutputStream(nombreArchivo);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Producto producto : productos) {
                oos.writeObject(producto);
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        ArrayList<Producto> productos = new ArrayList<>();
        String archivo = "productos.dat";

        try {
            productos = cargarEnArchivo(archivo, productos);
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudieron cargar los productos.");
        }

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("1. Añadir producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Guardar en archivo");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del producto: ");
                    String nombre = sc.nextLine();

                    System.out.print("Código del producto: ");
                    int codigo = sc.nextInt();

                    System.out.print("Precio del producto: ");
                    double precio = sc.nextDouble();

                    productos = añadirProducto(productos, crearProducto(nombre, codigo, precio));
                    System.out.println("Producto añadido.");
                    break;

                case 2:
                    System.out.println("\nLISTA DE PRODUCTOS:");
                    mostrarProductos(productos);
                    break;

                case 3:
                    System.out.print("Ingrese el código del producto a eliminar: ");
                    int codEliminar = sc.nextInt();
                    productos = eliminarProducto(productos, codEliminar);
                    System.out.println("Producto eliminado si existía.");
                    break;

                case 4:
                    guardarEnArchivo(archivo, productos);
                    System.out.println("Archivo guardado correctamente.");
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 5);
        sc.close();
    }
}

// ✅ EJERCICIO PROPUESTO (solo un tipo de clase)
// Crea un programa que gestione un archivo con objetos de la clase Producto.
// El programa debe permitir:
// ➕ Añadir nuevos productos
// 🔍 Mostrar
// ❌ Eliminarlos por su código
// 💾 Guardarlos en productos.dat