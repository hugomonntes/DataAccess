package data_access.SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class Ex_sax17 extends DefaultHandler {

    ArrayList<String> generos = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (qName.equals("pelicula")) {
            String genero = atts.getValue("genero");
            if (genero != null && !generos.contains(genero)) { 
                generos.add(genero);
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Número de xéneros distintos: " + generos.size());
        System.out.println("Xéneros: " + generos);
    }
}
