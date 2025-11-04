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
