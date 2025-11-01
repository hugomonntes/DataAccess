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