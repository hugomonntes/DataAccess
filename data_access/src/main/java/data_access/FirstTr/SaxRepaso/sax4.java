package data_access.SaxRepaso;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class sax4 extends DefaultHandler {
    boolean flagGloes = false;
    boolean flagNombre = false;
    int numeroGoles = 0;
    int aux = 0;
    String nombre;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (flagGloes) {
            aux = Integer.parseInt(new String(ch, start, length));
            if (aux > numeroGoles) {
                numeroGoles = aux;
            }
        }

        if (flagNombre) {
            nombre = new String(ch,start,length);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println(numeroGoles);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equals("goals_scored")) {
            flagGloes = false;
        }

        if (qName.equals("name")) {
            flagNombre = false;
        }

        if (qName.equals("team")) {
             
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equalsIgnoreCase("goals_scored")) {
            flagGloes = true;
        }

        if (qName.equalsIgnoreCase("name")) {
            flagNombre = true;
        }
    }

}

// 4. ¿Qué equipo ha marcado más goles?
