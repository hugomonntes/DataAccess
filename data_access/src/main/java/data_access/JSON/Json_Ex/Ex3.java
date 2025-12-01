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

import javax.json.JsonValue;

public class Ex3 {
    // . Crea un método, usando Open Weather Map, que dada una longitud y latitud
    // y un cantidad X devuelva las X predicciones meteorológicas de las
    // localidades cercanas a esas coordenadas.

    public static JsonValue searchWeatherProxy(String longitud, String latitud, String quantity){
        JsonValue jValue = JsonUtils.leeJSON("http://api.openweathermap.org/data/2.5/find?lat="+latitud+"&lon="+longitud+"&cnt="+quantity+"&APPID=a975f935caf274ab016f4308ffa23453");
        return jValue;
    }

    public static void main(String[] args) {
        System.out.println(searchWeatherProxy("24.2333", "42.2555", "20"));
    }
}
