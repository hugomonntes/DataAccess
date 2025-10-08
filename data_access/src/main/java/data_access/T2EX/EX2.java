package data_access.T2EX;

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
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EX2 {

    public static void mostrarTitulosPeliculas(Document doc) {
        Node raiz, pelicula, nombreTitulo;
        NodeList hijosDeRaiz, hijosDePelicula;
        raiz = doc.getFirstChild();
        hijosDeRaiz = raiz.getChildNodes();
        for (int i = 0; i < hijosDeRaiz.getLength(); i++) {
            pelicula = hijosDeRaiz.item(i);
            if (pelicula.getNodeType() == Node.ELEMENT_NODE) {
                hijosDePelicula = pelicula.getChildNodes();
                // System.out.println(hijosDePelicula.item(i).getTextContent());
                for (int j = 0; j < hijosDePelicula.getLength(); j++) {
                    nombreTitulo = hijosDePelicula.item(i);
                    System.out.println(nombreTitulo.getTextContent());
                    // System.out.println(hijosDePelicula.item(i).getFirstChild().getTextContent());
                }
            }
        }

        // System.out.println(hijosDeRaiz.toString());
    }

    public static void getTitulos(Document doc) {
        NodeList titulos = doc.getElementsByTagName("titulo");
        for (int i = 0; i < titulos.getLength(); i++) {
            System.out.println(titulos.item(i).getFirstChild().getNodeValue());
        }
    }

    public static void main(String[] args) {
        // mostrarTitulosPeliculas(EX1.creaArbol("peliculas.xml"));
        getTitulos(EX1.creaArbol("peliculas.xml"));
    }
}

// 2. Crea un método que mostre os títulos das películas.
