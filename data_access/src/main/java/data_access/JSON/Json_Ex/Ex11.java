/*-
 * =====LICENSE-START=====
 * Java 11 Application
 * ------
 * Copyright (C) 2020 - 2025 Organization Name
 * ------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====LICENSE-END=====
 */
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
