package data_access.T2EX;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomRepaso {
    public static Document creaArbol(String ruta) {
        Document doc = null;
        try {
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
            factoria.setIgnoringComments(true);
            DocumentBuilder builder = factoria.newDocumentBuilder();
            doc = builder.parse(ruta);
        } catch (Exception e) {
            System.out.println("Error generando el árbol DOM: " + e.getMessage());
        }
        return doc;
    }

    // Crea un método que mostre os títulos das películas.
    public static void mostrarTitulos(Document document) {
        NodeList titulos = document.getElementsByTagName("titulo");
        for (int i = 0; i < titulos.getLength(); i++) {
            System.out.println(titulos.item(i).getTextContent());
        }
    }

    // Crea un método que mostre todas as películas xunto co nome e apelido do
    // seu/os
    // seus directores ademais do xénero ao que pertence.
    public static void mostrarPeliculasConDirector(Document doc) {
        NodeList titulos = doc.getElementsByTagName("titulo");
        for (int i = 0; i < titulos.getLength(); i++) {
            String titulo = titulos.item(i).getTextContent();
            System.out.println(titulo);
            NodeList director = doc.getElementsByTagName("director");
            System.out.println(director.item(i).getChildNodes().item(1).getTextContent());
            System.out.println(director.item(i).getChildNodes().item(3).getTextContent());
        }
    }

    // 5. Crea un método que mostre as películas que teñen máis n directores. Sendo
    // n un
    // parámetro que se lle pasa o método.
    public static void mostrarPeliculas(Document doc, int numeroMaximoDirectores) {
        NodeList listaPeliculas = doc.getElementsByTagName("pelicula");
        for (int i = 0; i < listaPeliculas.getLength(); i++) {
            Element pelicula = (Element) listaPeliculas.item(i);
            String titulo = pelicula.getElementsByTagName("titulo").item(0).getTextContent();
            NodeList directores = pelicula.getElementsByTagName("director");
            if (directores.getLength() < numeroMaximoDirectores) {
                System.out.println("Película: " + titulo + " Directores: " + directores.getLength());
            }
        }
    }

    public static void main(String[] args) {
        // mostrarTitulos(creaArbol("peliculas.xml"));
        mostrarPeliculas(creaArbol("peliculas.xml"), 2);
    }
}
