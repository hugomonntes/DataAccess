package data_access.JSON.Json_Ex;

import javax.json.JsonObject;

public class Ex6 {
    // 6. Crea un método que devuelva las coordenadas de una ciudad en Open
    // Weather Map.
    public static String showCoords(String cityName){
        JsonObject jObj = Ex1.searchWeather(cityName).asJsonObject();
        JsonObject jObj2 = jObj.asJsonObject();
        return Ex1.searchWeather(cityName).asJsonObject().getString("coord");
    }
    public static void main(String[] args) {
        System.out.println(showCoords("Ourense"));
    }
}
