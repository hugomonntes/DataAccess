package data_access.T2EX;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EX3 {
    // Crea un método que mostre todas as películas xunto co nome e apelido do
    // seu/os
    // seus directores ademais do xénero ao que pertence.

    public static void mostrarTitulosPeliculas(Document doc) {
        Node raiz, pelicula;
        NodeList hijosDeRaiz;
        NamedNodeMap np;
        raiz = doc.getFirstChild();
        hijosDeRaiz = raiz.getChildNodes();
        for (int i = 0; i < hijosDeRaiz.getLength(); i++) {
            pelicula = hijosDeRaiz.item(i);
            np = pelicula.getAttributes();
            for (int j = 0; j < np.getLength(); j++) {
                System.out.println(np.item(j).getTextContent());
            }
            System.out.println(pelicula.getTextContent());
        }
    }

    public static void main(String[] args) {
        mostrarTitulosPeliculas(EX1.creaArbol("peliculas.xml"));
    }
}
