package data_access.SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ex_sax15 extends DefaultHandler {
    String atributoGenero;
    String tituloPelicula;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String aa = new String(ch, start, length);
        if (aa.equals("El Señor de los Anillos")) {
            System.out.print(new String(ch, start, length) + " - " + atributoGenero);
        } else {
            System.out.print(new String(ch, start, length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        for (int i = 0; i < attributes.getLength(); i++) {
            if (attributes.getLocalName(i).equals("genero")) {
                atributoGenero = attributes.getValue(i);
            }
        }
    }
}

// 15. Crea un "manexador " que mostre todas as películas xunto co nome e
// apelido do seu/os seus directores ademais do xénero ao que pertence.