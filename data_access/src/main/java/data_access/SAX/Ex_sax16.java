package data_access.SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ex_sax16 extends DefaultHandler {

    private int n;
    private int contadorDirectores;
    private String titulo = "";
    private String elemento = "";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        elemento = qName;
        if (qName.equals("pelicula")) {
            contadorDirectores = 0; // reiniciar el contador por película
            titulo = "";
        } else if (qName.equals("director")) {
            contadorDirectores++;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String texto = new String(ch, start, length).trim();
        if (texto.length() > 0 && elemento.equals("titulo")) {
            titulo = texto;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("pelicula")) {
            if (contadorDirectores >= n) {
                System.out.println("Película: " + titulo + " (" + contadorDirectores + " directores)");
            }
        }
        elemento = "";
    }
}
