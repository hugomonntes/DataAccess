
import java.io.File;
import java.util.Scanner;


public class Ex7 {

    /*7. Escribir un método que reciba dos parámetros: el primero será el nombre de un
archivo y el segundo una operación al realizar (para ordenar se puede usar la
clase Collections) siendo estas:
? Opción n: contar el número de líneas y palabras del archivo.
? Opción A: crear un nuevo archivo con las líneas del archivo inicial ordenadas
forma ascendente siendo esta ordenación sensible al caso.
? Opción D: crear un nuevo archivo con las líneas del archivo inicial ordenadas
forma descendente siendo esta ordenación sensible al caso.
? Opción a: crear un nuevo archivo con las líneas del archivo inicial ordenadas
forma ascendente siendo la ordenación no sensible al caso.
? Opción d: crear un nuevo archivo con las líneas del archivo inicial ordenadas
forma descendente siendo la ordenación no sensible al caso. */
    public static void showMenu(String fileName, char option) {
        switch (option) {
            case 'n' -> System.out.println();
            case 'A' -> System.out.println();
            case 'D' -> System.out.println();
            case 'a' -> System.out.println();
            case 'd' -> System.out.println();
            default -> throw new AssertionError();
        }
    }

    public static int countNumberLines(String fileName){
        int counterTotal = 0;
        try (Scanner sc = new Scanner(new File(fileName))) {
            while(sc.hasNext()){
                counterTotal++;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return counterTotal;
    }

    public static int countNumberWords(String fileName){
        String[] totalWords = new String[]{};
        try (Scanner sc = new Scanner(new File(fileName))) {
            while(sc.hasNext()){
                totalWords = sc.nextLine().split("\\W+");
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return totalWords.length;
    }

    public static void main(String[] args) {
        System.out.println(countNumberLines("archivo.txt"));
        showMenu("", 'n');
    }
}
