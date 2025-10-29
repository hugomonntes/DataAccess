package data_access.JSON.Json_Ex;

import javax.json.JsonObject;
import javax.json.JsonValue;

public class Ex8 {
    public static void getDataProximityCity(String cityName) {
        JsonValue jv = Ex1.searchWeather(cityName);
        JsonObject j = jv.asJsonObject();
        double lon = j.getJsonNumber("lon").doubleValue();
        double lat = j.getJsonNumber("lat").doubleValue();
        JsonValue jValue = Ex3.searchWeatherProxy(lon + "", lat+"", "10");
        System.out.println(Ex7.getAllData(cityName));
    }

    public static void main(String[] args) {
        getDataProximityCity("Ourense");
    }
}

// 8. Crea un método que devuelva los datos anteriores para las X ciudades
// cercanas a una dada (incluyendo esta)

// "coord": {
// "lon": -8.7226,
// "lat": 42.2328
// },
// "weather": [
// {
// "id": 800,
// "main": "Clear",
// "description": "cielo claro",
// "icon": "01d"
// }
// ],
// "base": "stations",
// "main": {
// "temp": 292.29,
// "feels_like": 291.7,
// "temp_min": 292.29,
// "temp_max": 292.29,
// "pressure": 1021,
// "humidity": 55,
// "sea_level": 1021,
// "grnd_level": 1006
// },
// "visibility": 10000,
// "wind": {
// "speed": 2.06,
// "deg": 260
// },
// "clouds": {
// "all": 0
// },
// "dt": 1761584732,
// "sys": {
// "type": 1,
// "id": 6440,
// "country": "ES",
// "sunrise": 1761548504,
// "sunset": 1761586532
// },
// "timezone": 3600,
// "id": 3105976,
// "name": "Vigo",
// "cod": 200
// }