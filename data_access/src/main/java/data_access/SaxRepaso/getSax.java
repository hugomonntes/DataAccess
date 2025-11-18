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

        sax1 aa = new sax1();
        parser.parse(entradaXML, aa);
        System.out.println();
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        getSax("liga.xml");
    }
}