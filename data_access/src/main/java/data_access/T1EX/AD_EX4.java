package data_access.T1EX;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class AD_EX4 {
    public static void calcularCharMasRepetido(ArrayList<Character> chars){
        char masRepetido = ' ';
        int maxRepeticiones = 0;

        for (Character character : chars) {
            int contador = 0;
            for (int i = 0; i < chars.size(); i++) {
                if (character == chars.get(i)) {
                    contador++;
                }
            }
            if (contador > maxRepeticiones) {
                maxRepeticiones = contador;
                masRepetido = character;
            }
        }
        System.out.println("Carácter más repetido: '" + masRepetido + "' con " + maxRepeticiones + " repeticiones.");
    }

    public static ArrayList<Character> leerArchivo(String nombreArchivo) {
        ArrayList<Character> caracteres = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nombreArchivo))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                for (int i = 0; i < linea.length(); i++) {
                    caracteres.add(linea.charAt(i));
                }
            }
        } catch (Exception e) {
        }
        return caracteres;
    }
    public static void main(String[] args) {
        calcularCharMasRepetido(leerArchivo("a.txt"));
    }
}
