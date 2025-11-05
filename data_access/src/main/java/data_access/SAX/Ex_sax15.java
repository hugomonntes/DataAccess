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
            genero = atts.getValue("genero");
        }
    }

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void characters(char[] ch, int start, int length) throws SAXException {
        String texto = new String(ch, start, length).trim();
        if (texto.length() > 0) {
            if (elemento.equals("titulo")) {
                titulo += texto;
            } else if (elemento.equals("nombre")) {
                nombre += texto;
            } else if (elemento.equals("apellido")) {
                apellido += texto;
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
