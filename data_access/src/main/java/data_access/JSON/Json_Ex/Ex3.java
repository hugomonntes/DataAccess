package data_access.JSON.Json_Ex;

import javax.json.JsonValue;

public class Ex3 {
    // . Crea un método, usando Open Weather Map, que dada una longitud y latitud
    // y un cantidad X devuelva las X predicciones meteorológicas de las
    // localidades cercanas a esas coordenadas.

    public static void searchWeatherProxy(double longitud, double latitud, int quantity){
        JsonValue jValue = JsonUtils.leeJSON(" http://api.openweathermap.org/data/2.5/find?lat="+ latitud +"&lon="+longitud+"&cnt="+quantity+"&APPID=8f8dccaf02657071004202f05c1fdce0");
        System.out.println(jValue);
    }

    public static void main(String[] args) {
        searchWeatherProxy(42.555, 54.255, 10);
    }
}
