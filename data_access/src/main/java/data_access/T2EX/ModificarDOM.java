package data_access.T2EX;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class ModificarDOM {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
        Document doc = Prueba.creaArbol("archivo.xml");
        añadirPelicula(doc, "Cars", "Walt Disney", "2006", "Carreras");
        grabarDOM(doc, "archivo.xml");
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

    public static void añadirPelicula(Document doc, String nombre, String director, String año, String tipo) {
        Element nodoPelicula = doc.createElement("Película");
        nodoPelicula.setAttribute("genero", tipo);
        nodoPelicula.appendChild(doc.createTextNode("\n")); // Crear salto de línea

        Element nodoTitulo = doc.createElement("Título");
        Text textTitulo = doc.createTextNode(nombre);
        nodoPelicula.appendChild(nodoTitulo);
        nodoTitulo.appendChild(textTitulo);
        nodoPelicula.appendChild(doc.createTextNode("\n"));

        Element nodoDirector = doc.createElement("Director");
        Text textDirector = doc.createTextNode(director);
        nodoPelicula.appendChild(nodoDirector);
        nodoDirector.appendChild(textDirector);
        nodoPelicula.appendChild(doc.createTextNode("\n"));

        Element nodoEstreno = doc.createElement("Estreno");
        nodoEstreno.appendChild(doc.createTextNode(año));
        nodoPelicula.appendChild(nodoEstreno);
        nodoPelicula.appendChild(doc.createTextNode("\n"));

        Node raiz = doc.getFirstChild();
        raiz.appendChild(nodoPelicula);
    }
}
