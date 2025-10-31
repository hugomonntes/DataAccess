package data_access.SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ex_sax15 extends DefaultHandler {
    private String elemento = "";
    private String titulo = "";
    private String nombre = "";
    private String apellido = "";
    private String genero = "";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        elemento = qName;
        if (qName.equals("pelicula")) {
            titulo = "";
            nombre = "";
            apellido = "";
            genero = "";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String texto = new String(ch, start, length).trim();
        if (texto.length() > 0) {
            if (elemento.equals("titulo")) {
                titulo += texto;
            } else if (elemento.equals("nombre")) {
                nombre += texto;
            } else if (elemento.equals("apellido")) {
                apellido += texto;
            } else if (elemento.equals("genero")) {
                genero += texto;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("pelicula")) {
            System.out.println("Película: " + titulo);
            System.out.println("Director: " + nombre + " " + apellido);
            System.out.println("Género: " + genero);
        }
        elemento = "";
    }
}
