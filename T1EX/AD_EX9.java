
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

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

    public static void escribirArchivoBinario(String fileName) {
        try (FileInputStream fin = new FileInputStream(fileName);
                FileOutputStream fout = new FileOutputStream("alumnos.dat")) {
            int i;
            while ((i = fin.read()) != -1) {
                fout.write((char) i);
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int option;
        do {
            System.out.println("1.- Dar de alta nuevos alumnos.");
            System.out.println("2.- Consultar alumnos.");
            System.out.println("3.- Modificar alumnos.");
            System.out.println("4.- Borrar alumnos.");
            System.out.println("5.- Salir.");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Introduce el codigo del alumno: ");
                    int codigo = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Introduce el nombre del alumno: ");
                    String nombre = sc.nextLine();
                    System.out.println("Introduce la altura del alumno: ");
                    float altura = sc.nextFloat();
                    alumnos.add(new Alumno(codigo, nombre, altura));
                    escribirArchivoBinario(new Alumno(codigo, nombre, altura).toString());
                    break;
                case 2:
                    for (Alumno alumno : alumnos) {
                        System.out.println(alumno);
                    }
                    ;
                    break;
                case 3:
                    System.out.println("Introduce el codigo del alumno a modificar: ");
                    int cod = sc.nextInt();
                    for (Alumno alumno : alumnos) {
                        if (alumno.getCodigo() == cod) {
                            System.out.println("Introduce el nuevo nombre del alumno: ");
                            String nom = sc.nextLine();
                            System.out.println("Introduce la nueva altura del alumno: ");
                            float alt = sc.nextFloat();
                            alumno.setNombre(nom);
                            alumno.setAltura(alt);
                        }
                    }
                    ;
                    break;
                case 4:
                    System.out.println("Introduce el codigo del alumno a borrar: ");
                    int co = sc.nextInt();
                    boolean found = false;
                    for (Alumno alumno : alumnos) {
                        if (alumno.getCodigo() == co) {
                            alumnos.remove(alumno);
                            found = true;
                            break;
                        }
                    }
                    ;
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:

                    break;
            }

        } while (option != 5);
    }
}
