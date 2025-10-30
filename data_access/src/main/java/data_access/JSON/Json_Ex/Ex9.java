package data_access.JSON.Json_Ex;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Ex9 {
    public static void main(String[] args) {
        // https://opentdb.com/api.php?amount=20&category=18&difficulty=hard&type=multiple
        // System.out.println(JsonUtils.leeJSON("https://opentdb.com/api.php?amount=20&category=18&difficulty=hard&type=multiple"));
        JsonValue all = JsonUtils.leeJSON("https://opentdb.com/api.php?amount=20&category=18&difficulty=hard&type=multiple");
        JsonObject j = all.asJsonObject();
        JsonArray jArr = j.getJsonArray("results");
        for (JsonValue jsonValue : jArr) {
            JsonObject a = jsonValue.asJsonObject();
            String question = a.getString("question");
            String correctQuest = a.getString("correct_answer");
            JsonArray jArrr = a.getJsonArray("incorrect_answers");
            System.out.println(question);
            System.out.println("\t * " + correctQuest);
            for (JsonValue jsonValue2 : jArrr) {
                System.out.println("\t" + jsonValue2);
            }
        }
    }
}

// 9. Usando open trivia database: https://opentdb.com/api_config.php genera 20
// preguntas de informática, de dificultad alta, y muestra la preguntas y
// respuestas marcando las correctas con un *.