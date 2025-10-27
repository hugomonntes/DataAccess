package data_access.JSON.Json_Ex;

import javax.json.JsonObject;
import javax.json.JsonValue;

public class Ex6 {
    // 6. Crea un método que devuelva las coordenadas de una ciudad en Open
    // Weather Map.
    public static String showCoords(String cityName){
        JsonValue jValue = Ex1.searchWeather(cityName);
        JsonObject jObj = jValue.asJsonObject();
        JsonObject jObjCoord = jObj.getJsonObject("coord");
        double lon = jObjCoord.getJsonNumber("lon").doubleValue();
        double lat = jObjCoord.getJsonNumber("lat").doubleValue();
        return String.format("Longitud: %f, Latitud: %f", lon, lat);
    }
    public static void main(String[] args) {
        System.out.println(showCoords("Ourense"));
    }
}
