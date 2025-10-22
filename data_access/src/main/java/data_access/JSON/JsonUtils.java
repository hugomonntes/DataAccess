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
package data_access.JSON;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.net.ssl.HttpsURLConnection;

public class JsonUtils {
    public static JsonValue leeJSON(String ruta) {
        try {
            if (ruta.toLowerCase().startsWith("http://")) {
                return leerHttp(ruta);
            } else if (ruta.toLowerCase().startsWith("https://")) {
                return leerHttps(ruta);
            } else {
                return leerFichero(ruta);
            }
        } catch (IOException e) {
            System.out.println("Error procesando documento Json " +
                    e.getLocalizedMessage());
            return null;
        }
    }

    public static JsonValue leerFichero(String ruta) throws FileNotFoundException {
        try (JsonReader reader = Json.createReader(new FileReader(ruta))) {
            return reader.read();
            /*
             * JsonStructure jsonSt = reader.read();
             * System.out.println(jsonSt.getValueType());
             * JsonObject jsonObj = reader.readObject();
             * System.out.println(jsonObj.getValueType());
             * JsonArray jsonArr = reader.readArray();
             * System.out.println(jsonArr.getValueType());
             */
        }
    }

    public static JsonValue leerHttp(String direccion) throws IOException {
        URL url = new URL(direccion);
        try (InputStream is = url.openStream();
                JsonReader reader = Json.createReader(is)) {
            return reader.read();
        }
    }

    public static JsonValue leerHttps(String direccion) throws IOException {
        URL url = new URL(direccion);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        try (InputStream is = conn.getInputStream();
                JsonReader reader = Json.createReader(is)) {
            return reader.read();
        } finally {
            conn.disconnect();
        }
    }

    public static void main(String[] args) {
        System.out.println(leeJSON("https://api.football-data.org/v4/matches"));
    }
}
