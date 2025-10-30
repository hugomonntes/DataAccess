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

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Ex8 {
    public static void getAllData(JsonValue jValue2){
        // String dateTime = unixTimeToString((long)jValue.asJsonObject().getInt("dt"));
        JsonValue jValue = Ex1.searchWeather("Ourense");
        JsonObject all = jValue.asJsonObject();
        JsonObject main = all.getJsonObject("main");
        double temp = (double)main.getInt("temp");
        int humidity = main.getInt("humidity");
        JsonArray weather = all.getJsonArray("weather");
        String probabilidad = "";
        for (JsonValue jsonValue : weather) {
            JsonObject probObj = jsonValue.asJsonObject();
            probabilidad = probObj.getString("description");
        }
        JsonObject wind = all.getJsonObject("wind");
        double windSpeed = wind.getJsonNumber("speed").doubleValue();
        JsonObject clouds = all.getJsonObject("clouds");
        int cloudsNumber = clouds.getInt("all");

        for (int i = 0; i < 10; i++) {
            System.out.println("Temperatura: " + temp+i + " | Nubes: " + cloudsNumber+i + " | Humedad: " + humidity+i + " | Probabilidad: " + probabilidad+i + " | Velocidad del viento:" + windSpeed+i);
        }
    }

    public static void getDataProximityCity(String cityName) {
        JsonValue jv = Ex1.searchWeather(cityName);
        JsonObject j = jv.asJsonObject();
        JsonObject coords = j.getJsonObject("coord");
        double lat = coords.getJsonNumber("lat").doubleValue();
        double lon = coords.getJsonNumber("lon").doubleValue();
        JsonValue jValue = Ex3.searchWeatherProxy(lon + "", lat + "", "10");
        // System.out.println(jValue);
        getAllData(jValue);
    }

    public static void main(String[] args) {
        getDataProximityCity("Ourense");
    }
}

// 8. Crea un método que devuelva los datos anteriores para las X ciudades
// cercanas a una dada (incluyendo esta)
