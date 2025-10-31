package data_access.JSON.Json_Ex;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Ex10 {
    private static String API_URL_SPORTS = "https://app.ticketmaster.com/discovery/v2/events.json?classificationName=sports&countryCode=ES&apikey=AMXR5Rf8zlr7oGucsebGKvDCLOQmGUGE";
    
    public static void showAllEventsFromCountry(String API_ROOT){
        if (!API_ROOT.isEmpty() || API_ROOT != null) {
            JsonValue jsonResponse = JsonUtils.leeJSON(API_ROOT);
            JsonObject rootObject = jsonResponse.asJsonObject();
            JsonObject _embeddedObject = rootObject.getJsonObject("_embedded");
            JsonArray eventsArray = _embeddedObject.getJsonArray("events");
            for (JsonValue event : eventsArray) {
                JsonObject eventObject = event.asJsonObject();
                String eventName = eventObject.getString("name");
                System.out.println(eventName);
            }
        }
    }
    
    public static void main(String[] args) {
        showAllEventsFromCountry(API_URL_SPORTS);
    }
}

// 10. Crea un método que dado un tipo nos muestre todos los eventos de un país
// en concreto. Usa para ello Ticketmaster.

// API_ROOT https://app.ticketmaster.com/discovery/v2/events.json?classificationName="+tipo_evento+"&countryCode=ES&apikey=AMXR5Rf8zlr7oGucsebGKvDCLOQmGUGE