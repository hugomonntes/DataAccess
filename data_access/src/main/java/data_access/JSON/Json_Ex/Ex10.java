package data_access.JSON.Json_Ex;

import java.util.ArrayList;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Ex10 {
    public static String API_URL_SPORTS = "https://app.ticketmaster.com/discovery/v2/events.json?classificationName=sports&countryCode=ES&apikey=AMXR5Rf8zlr7oGucsebGKvDCLOQmGUGE";

    public static JsonArray showAllEventsFromCountry(String API_ROOT) {
        JsonValue jsonResponse = JsonUtils.leeJSON(API_ROOT);
        JsonObject rootObject = jsonResponse.asJsonObject();
        JsonObject _embeddedObject = rootObject.getJsonObject("_embedded");
        JsonArray eventsArray = _embeddedObject.getJsonArray("events");
        return eventsArray;
    }

    public static void main(String[] args) {
        JsonArray eventsArray = showAllEventsFromCountry(API_URL_SPORTS);
        for (JsonValue event : eventsArray) {
            JsonObject eventObject = event.asJsonObject();
            String eventName = eventObject.getString("name");
            System.out.println(eventName);
        }
    }
}

// 10. Crea un método que dado un tipo nos muestre todos los eventos de un país
// en concreto. Usa para ello Ticketmaster.

// API_ROOT
// https://app.ticketmaster.com/discovery/v2/events.json?classificationName="+tipo_evento+"&countryCode=ES&apikey=AMXR5Rf8zlr7oGucsebGKvDCLOQmGUGE