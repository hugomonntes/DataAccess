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

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Ex7 {
    // Crea un método que devuelva: fecha, temperatura, humedad, probabilidad de
    // cielo con nubes, velocidad del viento y pronóstico del tiempo.
    // La fecha es el parámetro dt. Se puede convertir, por ejemplo a String con:
    public static String unixTimeToString(long unixTime) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Instant.ofEpochSecond(unixTime).atZone(ZoneId.of("GMT+1")).format(formatter);
    }

    public static String getAllData(String cityName){
        JsonValue jValue = Ex1.searchWeather(cityName);
        String dateTime = unixTimeToString((long)jValue.asJsonObject().getInt("dt"));
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

        return "Fecha: " + dateTime + " | Temperatura: " + temp + " | Nubes: " + cloudsNumber + " | Humedad: " + humidity + " | Probabilidad: " + probabilidad + " | Velocidad del viento:" + windSpeed;
    }

    public static void main(String[] args) {
        System.out.println(getAllData("Ourense"));
    }
}
// pronóstico del tiempo.
//  "coord": {
//     "lon": -8.7226,
//     "lat": 42.2328
//   },
//   "weather": [
//     {
//       "id": 800,
//       "main": "Clear",
//       "description": "cielo claro",
//       "icon": "01d"
//     }
//   ],
//   "base": "stations",
//   "main": {
//     "temp": 292.29,
//     "feels_like": 291.7,
//     "temp_min": 292.29,
//     "temp_max": 292.29,
//     "pressure": 1021,
//     "humidity": 55,
//     "sea_level": 1021,
//     "grnd_level": 1006
//   },
//   "visibility": 10000,
//   "wind": {
//     "speed": 2.06,
//     "deg": 260
//   },
//   "clouds": {
//     "all": 0
//   },
//   "dt": 1761584732,
//   "sys": {
//     "type": 1,
//     "id": 6440,
//     "country": "ES",
//     "sunrise": 1761548504,
//     "sunset": 1761586532
//   },
//   "timezone": 3600,
//   "id": 3105976,
//   "name": "Vigo",
//   "cod": 200
// }
