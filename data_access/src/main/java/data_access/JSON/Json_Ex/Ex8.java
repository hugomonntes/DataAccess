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

import static data_access.JSON.Json_Ex.Ex7.unixTimeToString;

public class Ex8 {
    public static void getAllData(JsonObject j) {
        JsonArray list = j.getJsonArray("list");
        for (int i = 0; i < list.size(); i++) {
            JsonObject jo = list.getJsonObject(i);
            long fecha = jo.getInt("dt");
            String cityName = jo.getString("name");
            JsonObject main = jo.getJsonObject("main");

            double temp = main.getJsonNumber("temp").doubleValue();
            int humedad = main.getInt("humidity");

            JsonObject nubes = jo.getJsonObject("clouds");
            int prob_nubes = nubes.getInt("all");

            JsonObject viento = jo.getJsonObject("wind");
            double velocidad = viento.getJsonNumber("speed").doubleValue();

            JsonArray tiempo = jo.getJsonArray("weather");
            JsonObject pronostico = tiempo.getJsonObject(0);
            String descripcion = pronostico.getString("description");

            System.out.printf(
                    "Ciudad: %s, Fecha: %s, Tº: %f, humedad: %d, porc.nubes: %d, vel.viento: %f, pronostico: %s\n",
                    cityName, unixTimeToString(fecha), temp, humedad, prob_nubes, velocidad, descripcion);
        }
    }

    public static void getDataProximityCity(String cityName) {
        JsonValue jv = Ex1.searchWeather(cityName);
        JsonObject j = jv.asJsonObject();
        JsonObject coords = j.getJsonObject("coord");
        double lat = coords.getJsonNumber("lat").doubleValue();
        double lon = coords.getJsonNumber("lon").doubleValue();
        JsonObject jValue = Ex3.searchWeatherProxy(lon + "", lat + "", "10").asJsonObject();
        getAllData(jValue);
    }

    public static void main(String[] args) {
        getDataProximityCity("Ourense");
    }
}

// 8. Crea un método que devuelva los datos anteriores para las X ciudades
// cercanas a una dada (incluyendo esta)
