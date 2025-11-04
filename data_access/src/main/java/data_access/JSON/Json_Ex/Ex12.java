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

import java.util.ArrayList;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Ex12 {
    public static void showWeatherAsCity(ArrayList<String> cityNames) {
        for (String city : cityNames) {
            JsonValue jValue = Ex1.searchWeather(city);
            JsonObject all = jValue.asJsonObject();
            JsonObject main = all.getJsonObject("main");
            double temp = (double) main.getInt("temp");
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

            System.out.println(
                    "Temperatura: " + temp + " | Nubes: " + cloudsNumber + " | Humedad: " + humidity
                            + " | Probabilidad: " + probabilidad + " | Velocidad del viento:" + windSpeed);
        }
    }

    public static void main(String[] args) { // Fix PalmaDeMallorca no me lo encuntra ni quitandole los espacios Puse Palma en vez de todo el nombre
        showWeatherAsCity(Ex11.showPlaceInfo(Ex10.showAllEventsFromCountry(Ex10.API_URL_SPORTS)));
    }
}

// 12. ¿Cuál es el tiempo actual de cada ciudad en donde se desarrollen los
// eventos encontrados? Tengo que buscar la city y aplicar la api de weather
