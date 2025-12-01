/*-
 * =====LICENSE-START=====
 * Java 11 Application
 * ------
 * Copyright (C) 2020 - 2025 Organization Name
 * ------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====LICENSE-END=====
 */
package data_access.T2;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class dom {
    public static Document creaArbol(String ruta) {
        Document doc = null;
        try {
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
            factoria.setIgnoringComments(true);
            DocumentBuilder builder = factoria.newDocumentBuilder();
            doc = builder.parse(ruta);
            System.out.println("Arbol DOM creado");
        } catch (Exception e) {
            System.out.println("Error generando el árbol DOM: " + e.getMessage());
        }
        return doc;
    }

    public static void main(String[] args) {
        Document doc = creaArbol("archivo.xml");
        Node nodoPeliculas = doc.getFirstChild();
        NodeList pelicula = nodoPeliculas.getChildNodes();
        NodeList hijosPelicula;
        Node p, hijo;
        NamedNodeMap atributos;

        NodeList titulos;
        titulos = doc.getElementsByTagName("Título");
        for (int i = 0; i < titulos.getLength(); i++) {
            System.out.println(titulos.item(i).getTextContent());
        }


        for (int i = 0; i < pelicula.getLength(); i++) {
            p = pelicula.item(i);
            if (p.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println(p.getNodeName());
                hijosPelicula = p.getChildNodes();
                if (p.hasAttributes()) {
                    atributos = p.getAttributes();
                    for (int j = 0; j < atributos.getLength(); j++) {
                        System.out.println(atributos.item(j).getNodeName());
                        System.out.println(atributos.item(j).getNodeValue());
                    }
                }
                for (int j = 0; j < hijosPelicula.getLength(); j++) {
                    hijo = hijosPelicula.item(i);
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println(p.getNodeName() + " - " + hijo.getTextContent());
                    }
                }
            }
        }
    }
}
