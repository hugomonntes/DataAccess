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
package data_access.JSON.Json_Ex;

import javax.json.JsonObject;
import javax.json.JsonValue;

public class Ex8 {
    public static void getDataProximityCity(String cityName) {
        JsonValue jv = Ex1.searchWeather(cityName);
        JsonObject j = jv.asJsonObject();
        double lon = j.getJsonNumber("lon").doubleValue();
        double lat = j.getJsonNumber("lat").doubleValue();
        JsonValue jValue = Ex3.searchWeatherProxy(lon + "", lat + "", "10");
        System.out.println(Ex7.getAllData(cityName));
    }

    public static void main(String[] args) {
        getDataProximityCity("Ourense");
    }
}

// 8. Crea un método que devuelva los datos anteriores para las X ciudades
// cercanas a una dada (incluyendo esta)


