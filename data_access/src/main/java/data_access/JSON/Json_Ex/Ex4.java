package data_access.JSON.Json_Ex;

import javax.json.JsonObject;
import javax.json.JsonValue;

public class Ex4 {
    // 4. Crea un método que devuelva el id de una ciudad en Open Weather Map.
    public static int searchIdPerCity(String city){
        JsonValue jValue = Ex1.searchWeather(city);
        JsonObject jObject = jValue.asJsonObject();
        return jObject.getInt("id");
    }
    public static void main(String[] args) {
        System.out.println(searchIdPerCity("Vigo"));
    }
}
