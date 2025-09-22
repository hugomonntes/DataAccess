import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class AD_EX4 {
    @SuppressWarnings({"BoxedValueEquality", "NumberEquality"})
    public static void calcularCharMasRepetido(ArrayList<Character> chars){
        for (Character character : chars) {
            for (int i = 0; i < chars.size(); i++) {
                if (character == chars.get(i)) {
                    
                }
            }
            System.out.println(character);
        }
    }

    public static ArrayList<Character> filtroLeerArchivo(String nombreLeerArchivo){
        ArrayList<Character> charDivididas = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nombreLeerArchivo))) {
            while (sc.hasNext()) {
                String textoEntero = sc.nextLine();
                for (int i = 0; i < textoEntero.length(); i++) {
                    charDivididas.add(textoEntero.charAt(i));
                }
            }
        } catch (Exception e) {
        }
        return charDivididas;
    }

    public static void main(String[] args) {
        calcularCharMasRepetido(filtroLeerArchivo("a.txt"));
    }
}
