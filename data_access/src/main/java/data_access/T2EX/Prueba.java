import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Prueba {
    public static Document creaArbol(String ruta) {
        Document doc = null;
        try {
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
            factoria.setIgnoringComments(true);
            DocumentBuilder builder = factoria.newDocumentBuilder();
            doc = builder.parse(ruta);
            System.out.println("Arbol genereadop");
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Error generando el árbol DOM: " + e.getMessage());
        }
        return doc;
    }

    public static void leerArbol(Document doc, String tituloPelicula) {
        NodeList titulos = doc.getElementsByTagName("Título");
        Node titulo;
        Element padre;
        NodeList aux, aux2;
        for (int i = 0; i < titulos.getLength(); i++) {
            titulo = titulos.item(i);
            if (titulo.getTextContent().trim().equals(tituloPelicula)) {
                padre = (Element) titulo.getParentNode();
                aux = padre.getElementsByTagName("Director");
                aux2 = padre.getElementsByTagName("Estreno");
                System.out.println(aux.item(0).getTextContent());
                System.out.println(aux2.item(0).getTextContent());
            }
        }
    }

    public static void main(String[] args) {
        Document doc = creaArbol("archivo.xml");
        leerArbol(doc, "Indiana Jones y la última cruzada");
    }
}
