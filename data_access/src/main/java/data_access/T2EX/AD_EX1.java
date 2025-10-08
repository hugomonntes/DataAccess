package data_access.T2EX;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class AD_EX1 {
    // 1. Crea un método que lea o ficheiro peliculas.xml e cree a árbore DOM.
    public static Document creaArbol(String ruta) {
        Document doc = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setIgnoringComments(true);
            DocumentBuilder builder = dbf.newDocumentBuilder();
            doc = builder.parse(ruta);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Error generando el árbol DOM: " + e.getMessage());
        }
        return doc;
    }

    public static void leerArchivoXML(Document doc){
        Node raiz;
        raiz = doc.getFirstChild();
        System.out.println(raiz.toString());
    }

    public static void main(String[] args) {
        leerArchivoXML(creaArbol("C:\\Users\\Hugo Montes\\Documents\\DataAccess\\data_access\\src\\main\\java\\data_access\\peliculas.xml"));
    }
}
