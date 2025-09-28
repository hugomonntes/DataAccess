package data_access.T1EX;

public class Alumno {
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

    int codigo;
    String nombre;
    float altura;

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public float getAltura() {
        return altura;
    }

    public Alumno(int codigo, String nombre, float altura) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "altura: " + altura + ", codigo: " + codigo + ", nombre: " + nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Alumno && this.codigo == ((Alumno)obj).codigo) {
            return true;
        }
        return false;
    }
}
