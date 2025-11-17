package data_access.T2EX;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

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

    // 6. Cantos xéneros diferentes de películas hai? Cales son?
    public static void mostrarGeneros(Document doc) {
        ArrayList<String> generos = new ArrayList<>();
        String geneross = "";
        NodeList peliculas = doc.getElementsByTagName("pelicula");
        for (int i = 0; i < peliculas.getLength(); i++) {
            Element pelicula = (Element) peliculas.item(i);
            if (!generos.contains(pelicula.getAttribute("genero"))) {
                generos.add(pelicula.getAttribute("genero"));
            }
        }
        for (String string : generos) {
            geneross += string + " / ";
        }
        System.out.println("Numero de generos: " + generos.size() + " son: " + geneross);
    }

    // 7. Crea dous métodos:
    // Ø Dado o título dunha película engádalle, se non existe, un atributo pasado
    // como parámetro.
    public static void añadirAtributo(Document doc, String atribName){
        NodeList peliculas = doc.getElementsByTagName("pelicula");
        for (int i = 0; i < peliculas.getLength(); i++) {
            Element pelicula = (Element) peliculas.item(i);
            if (pelicula.getAttribute(atribName) == null) {
                pelicula.setAttribute(atribName, "hola");
            }
        }
    }


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
        // mostrarTitulos(creaArbol("peliculas.xml"));
        // mostrarGeneros(creaArbol("peliculas.xml"));
        añadirAtributo(creaArbol("peliculas.xml"), "hola");
        grabarDOM(creaArbol("peliculas.xml"), "C:\\Users\\Hugo Montes\\Documents\\DataAccess\\data_access\\src\\main\\java\\data_access\\T2EX\\archivo.xml");
    }

    public static void grabarDOM(Document document, String ficheroSalida)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, FileNotFoundException {
        DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
        DOMImplementationLS ls = (DOMImplementationLS) registry.getDOMImplementation("XML 3.0 LS 3.0");
        // Se crea un destino vacio
        LSOutput output = ls.createLSOutput();
        output.setEncoding("UTF-8");
        // Se establece el flujo de salida
        output.setByteStream(new FileOutputStream(ficheroSalida));
        // output.setByteStream(System.out);
        // Permite escribir un documento DOM en XML
        LSSerializer serializer = ls.createLSSerializer();
        // Se establecen las propiedades del serializador
        serializer.setNewLine("\r\n");
        serializer.getDomConfig().setParameter("format-pretty-print", true);
        // Se escribe el documento ya sea en un fichero o en una cadena de texto
        serializer.write(document, output);
        // String xmlCad=serializer.writeToString(document);
    }
}
