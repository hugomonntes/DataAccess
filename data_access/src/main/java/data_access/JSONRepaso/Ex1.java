package data_access.JSONRepaso;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Ex1 {
    public static void mostrarEquipo(JsonValue jvalue){
        JsonObject todo = jvalue.asJsonObject();
        JsonArray aa = todo.getJsonArray("ciclistas");
        for (int i = 0; i < aa.size(); i++) {
            JsonObject cadaCiclista = aa.get(i).asJsonObject();
            JsonArray equipos = cadaCiclista.getJsonArray("equipos");
            for (int j = 0; j < equipos.size(); j++) {
                System.out.println(equipos.getString(j));
            }
        }
    }
    public static void main(String[] args) {
        JsonValue all = JsonUtils.leeJSON("data_access/src/main/java/data_access/JSONRepaso/ciclistas.json");
        mostrarEquipo(all);
    }
}
