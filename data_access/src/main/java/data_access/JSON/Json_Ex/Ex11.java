package data_access.JSON.Json_Ex;

import java.util.ArrayList;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Ex11 {
    public static ArrayList<String> showPlaceInfo(JsonArray eventsArray) {
        ArrayList<String> cityNames = new ArrayList<>();
        for (JsonValue event : eventsArray) {
            JsonObject eventObjs = event.asJsonObject();
            String eventName = eventObjs.getString("name");
            JsonObject _embedded = eventObjs.getJsonObject("_embedded");
            JsonArray venues = _embedded.getJsonArray("venues");
            for (JsonValue venue : venues) {
                JsonObject venueObj = venue.asJsonObject();
                String locationName = venueObj.getString("name");
                JsonObject cityObj = venueObj.getJsonObject("city");
                String cityName = cityObj.getString("name").replace("Palma de Mallorca", "Palma");
                cityNames.add(cityName);
                System.out.println(eventName + " - " + locationName + " - " + cityName);
            }
        }
        return cityNames;
    }

    public static void main(String[] args) {
        showPlaceInfo(Ex10.showAllEventsFromCountry(Ex10.API_URL_SPORTS));
    }
}

// 11. Crea dos métodos que, para cada evento anterior, muestre la información
// detallada de cada lugar en el que se desarrolle y la información detallada
// del
// evento.