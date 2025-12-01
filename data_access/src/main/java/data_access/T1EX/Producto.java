package data_access.T1EX;

import java.io.Serializable;

public class Producto implements Serializable {
    private String nombre;
    private int codigo;
    private double precio;

    public Producto(String nombre, int codigo, double precio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Producto { nombre='" + nombre + "', codigo=" + codigo + ", precio=" + precio + " }";
    }
}
