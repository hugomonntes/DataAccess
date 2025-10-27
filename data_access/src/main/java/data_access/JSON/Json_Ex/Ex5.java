package data_access.JSON.Json_Ex;

public class Ex5 {
    // 5. Crea un método que devuelva el nombre de una ciudad en Open Weather Map.
    public static String cityName(double latitud, double longitud){
        return Ex2.searchWeatherAsLatLong(latitud, longitud).asJsonObject().getString("name");
    }
    public static void main(String[] args) {
        System.out.println(cityName(54.153, 86.352));
    }
}
