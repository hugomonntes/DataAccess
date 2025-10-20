package data_access.SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ex_sax15 extends DefaultHandler {
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
        System.out.print(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        System.out.print("</" + qName + ">");

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("pelicula")) {
            for (int i = 0; i < attributes.getLength(); i++) {
                if(attributes.getLocalName(i).equals("genero")){
                    System.out.print("<" + qName + " " + attributes.getLocalName(i) + "=\"" + attributes.getValue(i) + "\">");
                }
            }
        } else {
            System.out.print("<" + qName + ">");
        }
    }
}

// 15. Crea un "manexador " que mostre todas as películas xunto co nome e
// apelido do seu/os seus directores ademais do xénero ao que pertence.