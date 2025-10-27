package data_access.JSON.Json_Ex;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
        JsonObject jObj = jValue.asJsonObject();
        JsonObject main = jObj.getJsonObject("main");
        double temp = (double)main.getInt("temp");
        return dateTime + "//" + temp;
    }

    public static void main(String[] args) {
        System.out.println(getAllData("Ourense"));
    }
}
