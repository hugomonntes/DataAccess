
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AD_EX4 {
//     @SuppressWarnings({"BoxedValueEquality", "NumberEquality"})
//     public static void calcularCharMasRepetido(ArrayList<Character> chars){
//         for (Character character : chars) {
//             for (int i = 0; i < chars.size(); i++) {
//                 if (character == chars.get(i)) {

//                 }
//             }
//             System.out.println(character);
//         }
//     }
//     public static ArrayList<Character> filtroLeerArchivo(String nombreLeerArchivo){
//         ArrayList<Character> charDivididas = new ArrayList<>();
//         try (Scanner sc = new Scanner(new File(nombreLeerArchivo))) {
//             while (sc.hasNext()) {
//                 String textoEntero = sc.nextLine();
//                 for (int i = 0; i < textoEntero.length(); i++) {
//                     charDivididas.add(textoEntero.charAt(i));
//                 }
//             }
//         } catch (Exception e) {
//         }
//         return charDivididas;
//     }
//     public static void main(String[] args) {
//         calcularCharMasRepetido(filtroLeerArchivo("a.txt"));
//     }
    public static void main(String[] args) {
        FileReader fr = null;
        Map<Character, Integer> frecuencias = new HashMap<>();
        try {
            fr = new FileReader("ejemplo.txt");
            int codigoCaracter;
            while ((codigoCaracter = fr.read()) != -1) {
                char caracterLeido = (char) codigoCaracter;
                frecuencias.put(caracterLeido, frecuencias.getOrDefault(caracterLeido, 0) + 1);
            }
            fr.close();
        } catch (IOException e) {
            System.out.println("error al leer el archivo");
        }
        char caracterMasFrecuente = ' ';
        int maxFrecuencia = 0;
        for (Map.Entry<Character, Integer> entry : frecuencias.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
                caracterMasFrecuente = entry.getKey();
            }
        }
        System.out.println("El caracter mas usado es: " + caracterMasFrecuente + " con una frecuencia de: " + maxFrecuencia);

    }
}
