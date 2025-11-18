package data_access;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.glassfish.json.JsonUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import data_access.JSON.Json_Ex.JsonUtils;
import data_access.T2EX.ModificarDOM;

public class liga {
    public static Document creaArbol(String ruta) {
        Document doc = null;
        try {
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
            factoria.setIgnoringComments(true);
            DocumentBuilder builder = factoria.newDocumentBuilder();
            doc = builder.parse(ruta);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Error generando el árbol DOM: " + e.getMessage());
        }
        return doc;
    }

    // 1. Temporada en que se ha desarrollado la jornada de liga.
    public static void añoDeLiga(Document doc) {
        NodeList temporada = doc.getElementsByTagName("temporada");
        System.out.println(temporada.item(0).getTextContent());
    }

    // 2. Número de partidos de la jornada.
    public static void contarPartidosJornada(Document doc) {
        NodeList eventos = doc.getElementsByTagName("evento");
        System.out.println(eventos.getLength());
    }

    // 3. Nombre de los equipos y fechas en que se ha desarrollado los partidos.
    public static void equiposFechaPartido(Document doc) {
        NodeList eventos = doc.getElementsByTagName("evento");
        for (int i = 0; i < eventos.getLength(); i++) {
            Node evento = eventos.item(i);
            if (evento.getNodeType() == Node.ELEMENT_NODE) {
                Element cada = (Element) evento;
                System.out.println(cada.getElementsByTagName("fecha").item(0).getTextContent());
                System.out.println(cada.getElementsByTagName("equipolocal").item(0).getTextContent());
                System.out.println(cada.getElementsByTagName("equipovisitante").item(0).getTextContent());
                System.out.println();
            }
        }
    }

    // 4. ¿Qué equipo ha marcado más goles?
    public static void equipoMasGoleador(Document doc) {
        NodeList equipo = doc.getElementsByTagName("team");
        int contadorMasGoles = 0;
        String nombreEquipo = "";
        for (int i = 0; i < equipo.getLength(); i++) {
            Node cadaEquipo = equipo.item(i);
            if (cadaEquipo.getNodeType() == Node.ELEMENT_NODE) {
                Element equipoos = (Element) cadaEquipo;
                int numeroGoles = Integer
                        .parseInt(equipoos.getElementsByTagName("goals_scored").item(0).getTextContent());
                if (numeroGoles > contadorMasGoles) {
                    contadorMasGoles = numeroGoles;
                    nombreEquipo = equipoos.getElementsByTagName("name").item(0).getTextContent();
                }
            }
        }
        System.out.println(nombreEquipo + ": " + contadorMasGoles);
    }

    // 5. ¿Qué partido ha jugado el colista?
    public static void partidoColista(Document doc){
        NodeList listaTeams = doc.getElementsByTagName("team");
        Element elementoColista = (Element) listaTeams.item(listaTeams.getLength() - 1);
        String nombreColista = elementoColista.getElementsByTagName("name").item(0).getTextContent();
        System.out.println(nombreColista);


        String local = "";
        String visitante = "";
        NodeList eventos = doc.getElementsByTagName("evento");
        for (int i = 0; i < eventos.getLength(); i++) {
            Node evento = eventos.item(i);
            if (evento.getNodeType() == Node.ELEMENT_NODE) {
                Element eventoEle = (Element) evento;
                if (eventoEle.getElementsByTagName("equipolocal").item(0).getTextContent().equals("Osasuna")) {
                    local = eventoEle.getElementsByTagName("equipolocal").item(0).getTextContent();
                    visitante = eventoEle.getElementsByTagName("equipovisitante").item(0).getTextContent();
                } else if (eventoEle.getElementsByTagName("equipovisitante").item(0).getTextContent().equals("Osasuna")) {
                    local = eventoEle.getElementsByTagName("equipolocal").item(0).getTextContent();
                    visitante = eventoEle.getElementsByTagName("equipovisitante").item(0).getTextContent();
                }
            }
        }
        System.out.println(local + " - " + visitante);
    }

    public static void añadirElemento(Document doc, int horass){
        NodeList compe = doc.getElementsByTagName("competicion");
        Element duracion = doc.createElement("duracion");
        compe.item(0).appendChild(duracion);
        duracion.appendChild(doc.createTextNode("\n"));
        // Node horas = duracion.appendChild(doc.createElement("horas"));
        // horas.setTextContent(horass + "");
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
        // añoDeLiga(creaArbol("liga.xml"));
        // contarPartidosJornada(creaArbol("liga.xml"));
        // equiposFechaPartido(creaArbol("liga.xml"));
        // equipoMasGoleador(creaArbol("liga.xml"));
        // partidoColista(creaArbol("liga.xml"));
        añadirElemento(creaArbol("liga.xml"), 5);
        ModificarDOM.grabarDOM(creaArbol("liga.xml"), "aa.xml");
    }
}

// 6. ¿Qué equipo ha empatado más?
// 7. Indica la clasificación de los equipos que han jugado el tercer partido de
// la jornada.