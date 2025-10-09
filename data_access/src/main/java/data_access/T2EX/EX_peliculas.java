package data_access.T2EX;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class EX_peliculas {
    // 1. Crea un método que lea o ficheiro peliculas.xml e cree a árbore DOM.
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

    // 2. Crea un método que mostre os títulos das películas.
    public static void mostrarTitulos(Document doc) {
        NodeList peliculas = doc.getElementsByTagName("pelicula");
        for (int i = 0; i < peliculas.getLength(); i++) {
            Element pelicula = (Element) peliculas.item(i);
            String titulo = pelicula.getElementsByTagName("titulo").item(0).getTextContent();
            System.out.println(titulo);
        }
    }

    // 3. Crea un método que mostre todas as películas xunto co nome e apelido do seu/os seus directores ademais do xénero ao que pertence.
    public static void mostrarPeliculas(Document doc) {
        Node filmoteca, peliculaNode, tituloDirector;
        NodeList pelicula, hijosPelicula;
        NamedNodeMap atributos;
        filmoteca = doc.getFirstChild();
        pelicula = filmoteca.getChildNodes();
        for (int i = 0; i < pelicula.getLength(); i++) {
            peliculaNode = pelicula.item(i);
            if (peliculaNode.hasAttributes()) {
                atributos = peliculaNode.getAttributes();
                for (int j = 0; j < atributos.getLength(); j++) {
                    if (atributos.item(j).getNodeName() == "genero") {
                        System.out.println("Genero: " + atributos.item(j).getTextContent());
                    }
                }
            }
            if (peliculaNode.getNodeType() == Node.ELEMENT_NODE) {
                hijosPelicula = peliculaNode.getChildNodes();
                for (int j = 0; j < hijosPelicula.getLength(); j++) {
                    tituloDirector = hijosPelicula.item(j);
                    if (tituloDirector.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println(tituloDirector.getNodeName() + " - " + tituloDirector.getTextContent());
                    }
                }
            }
        }
    }

    // 5. Crea un método que mostre as películas que teñen máis n directores. Sendo n un parámetro que se lle pasa o método.
    public static void contarDirectores(Document doc, int n) {
        Node filmotecaNode;
        Element peliculaNode;
        NodeList pelicula, directores, titulos;
        filmotecaNode = doc.getFirstChild();
        pelicula = filmotecaNode.getChildNodes();
        for (int i = 0; i < pelicula.getLength(); i++) {
            if (pelicula.item(i).getNodeType() == Node.ELEMENT_NODE) {
                peliculaNode = (Element) pelicula.item(i);
                directores = peliculaNode.getElementsByTagName("director");
                titulos = peliculaNode.getElementsByTagName("titulo");
                if (directores.getLength() > n) {
                    System.out.printf("%S: %s\n", titulos.item(0).getNodeName(), titulos.item(0).getTextContent());
                }
            }
        }
    }

    // 6. Cantos xéneros diferentes de películas hai? Cales son?
    public static void diferentesGeneros(Document doc) {
        Node filmotecaNode, peliculaNode;
        NodeList pelicula;
        ArrayList<String> generos = new ArrayList<>();
        NamedNodeMap atributos;
        filmotecaNode = doc.getFirstChild();
        pelicula = filmotecaNode.getChildNodes();
        for (int i = 0; i < pelicula.getLength(); i++) {
            if (pelicula.item(i).getNodeType() == Node.ELEMENT_NODE) {
                peliculaNode = (Element) pelicula.item(i);
                if (peliculaNode.hasAttributes()) {
                    atributos = peliculaNode.getAttributes();
                    for (int j = 0; j < atributos.getLength(); j++) {
                        if (atributos.item(j).getNodeName() == "genero") {
                            if (!generos.contains(atributos.item(j).getTextContent())) {
                                generos.add(atributos.item(j).getTextContent());
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < generos.size(); i++) {
            System.out.println(generos.get(i));
        }
        System.out.printf("Nº DE GENEROS DIFRENTES %d", generos.size());
    }

    // 7. Crea dous métodos:
    // Ø Dado o título dunha película engádalle, se non existe, un atributo pasado como parámetro.
    public static void añadirAtributo(Document doc, String tituloPelicula, String atributo) {
        NodeList peliculaPorTitulo = doc.getElementsByTagName("pelicula");
        NamedNodeMap atributosDePelicula;
        Element pelicula;
        for (int i = 0; i < peliculaPorTitulo.getLength(); i++) {
            atributosDePelicula = peliculaPorTitulo.item(i).getAttributes();
            for (int j = 0; j < atributosDePelicula.getLength(); j++) {
                if (!atributosDePelicula.item(j).getNodeName().equals(atributo)) {
                    pelicula = (Element) peliculaPorTitulo.item(i);
                    pelicula.setAttribute(atributo, "DIEGO COSTA");
                }
            }
        }
    }

    // Ø Dado o título dunha película e un atributo, se existe elimíneo.
    public static void eliminarAtributo(Document doc, String tituloPelicula, String atributo) {
        NodeList peliculas = doc.getElementsByTagName("pelicula");
        for (int i = 0; i < peliculas.getLength(); i++) {
            Element pelicula = (Element) peliculas.item(i);
            String titulo = pelicula.getElementsByTagName("titulo").item(0).getTextContent();
            if (titulo.equalsIgnoreCase(tituloPelicula) && pelicula.hasAttribute(atributo)) {
                pelicula.removeAttribute(atributo);
                System.out.println(atributo + "eliminado");
            }
        }
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
        ;
        serializer.getDomConfig().setParameter("format-pretty-print", true);
        // Se escribe el documento ya sea en un fichero o en una cadena de texto
        serializer.write(document, output);
        // String xmlCad=serializer.writeToString(document);
    }

    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
        String ruta = "peliculas.xml";
        Document doc = creaArbol(ruta);
        // mostrarTitulos(doc);
        // mostrarPeliculas(doc);
        // contarDirectores(doc, 1);
        añadirAtributo(doc, "El señor de los anillos", "diego costa");
        grabarDOM(doc, ruta);

    }
}