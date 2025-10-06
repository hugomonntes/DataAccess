package data_access.T2EX;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EX2 {

    public static void mostrarTitulosPeliculas(Document doc) {
        Node raiz, pelicula, nombreTitulo;
        NodeList hijosDeRaiz, hijosDePelicula;
        raiz = doc.getFirstChild();
        hijosDeRaiz = raiz.getChildNodes();
        for (int i = 0; i < hijosDeRaiz.getLength(); i++) {
            pelicula = hijosDeRaiz.item(i);
            if (pelicula.getNodeType() == Node.ELEMENT_NODE) {
                hijosDePelicula = pelicula.getChildNodes();
                // System.out.println(hijosDePelicula.item(i).getTextContent());
                for (int j = 0; j < hijosDePelicula.getLength(); j++) {
                    nombreTitulo = hijosDePelicula.item(i);
                    System.out.println(nombreTitulo.getTextContent());
                    // System.out.println(hijosDePelicula.item(i).getFirstChild().getTextContent());
                }
            }
        }

        // System.out.println(hijosDeRaiz.toString());
    }

    public static void getTitulos(Document doc) {
        NodeList titulos = doc.getElementsByTagName("titulo");
        for (int i = 0; i < titulos.getLength(); i++) {
            System.out.println(titulos.item(i).getFirstChild().getNodeValue());
        }
    }

    public static void main(String[] args) {
        // mostrarTitulosPeliculas(EX1.creaArbol("peliculas.xml"));
        getTitulos(EX1.creaArbol("peliculas.xml"));
    }
}

// 2. Crea un método que mostre os títulos das películas.