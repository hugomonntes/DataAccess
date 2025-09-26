package data_access.T1;


import java.io.FileReader;

public class Ex1 {

    static int contarChars(char charToCompare) {
        int contadorChars = 0;
        try (FileReader fr = new FileReader("archivo.txt")) {
            int i;
            while ((i = fr.read()) != -1) {
                if ((char) i == charToCompare) {
                    contadorChars++;
                }
            }
        } catch (Exception e) {
        }
        return contadorChars;
    }

    public static void main(String[] args) {
        System.out.println(contarChars('C'));
    }
}
