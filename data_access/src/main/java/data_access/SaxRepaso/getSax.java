package data_access.SaxRepaso;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class getSax {
    public static void getSax(String entradaXML) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        sax1 e1 = new sax1();
        parser.parse(entradaXML, e1);
        System.out.println();

        sax2 e2 = new sax2();
        parser.parse(entradaXML, e2);
        System.out.println();

        sax3 e3 = new sax3();
        parser.parse(entradaXML, e3);
        System.out.println();

        sax4 e4 = new sax4();
        parser.parse(entradaXML, e4);
        System.out.println();
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        getSax("liga.xml");
    }
}