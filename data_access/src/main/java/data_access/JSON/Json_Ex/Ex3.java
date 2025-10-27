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
