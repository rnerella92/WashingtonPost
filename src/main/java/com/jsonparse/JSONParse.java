package com.jsonparse;

import static spark.Spark.*;
import org.json.JSONObject;
import java.util.Iterator;
import org.json.JSONArray;
import java.util.Map;
import java.util.HashMap;

class JSONParse {

    /**
     * Starting point pf the application
     */
    public static void main(String[] args) throws Exception {

        /**
         * end point for json parse
         */
        post("/jsonparse", (request, response) -> {
            JSONObject obj = new JSONObject(request.body());
            handleJSONObject(obj);
            response.type("application/json");
            return obj;
        });

        /**
         * end point for scrabble boggle
         */

        post("/scrabbleboggle", (request, response) -> {
            JSONObject obj = new JSONObject(request.body());

            int result = helper(obj.get("sourceString").toString(), obj.get("finalString").toString());
            return result;
        });
    }

    private static void handleJSONObject(JSONObject jsonObject) throws Exception {
        if (jsonObject == null || jsonObject.isEmpty()) {
            return;
        }
        jsonObject.remove("password");
        Iterator<String> keys = jsonObject.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            if (jsonObject.get(key) instanceof JSONObject) {
                /**
                 * For handling nested objects
                 */
                handleJSONObject(jsonObject.getJSONObject(key));
            } else if (jsonObject.get(key) instanceof JSONArray) {
                handleJSONArray(jsonObject.getJSONArray(key));
            }
        }
        return;
    }

    private static void handleJSONArray(JSONArray jsonArray) throws Exception {
        /**
         * For handling JSON array Elements
         */
        if (jsonArray == null || jsonArray.isEmpty() || jsonArray.length() == 0) {
            return;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            handleJSONObject(jsonArray.getJSONObject(i));
        }
        return;
    }

    private static int helper(String sourceString, String finalString) {
        /**
         * base conditions to check inputs
         */
        if (sourceString == null || finalString == null) {
            return 0;
        }

        if (sourceString.length() == 0 || finalString.length() == 0) {
            return 0;
        }

        if (sourceString.length() < finalString.length()) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : sourceString.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int result = Integer.MAX_VALUE;
        for (char ch : finalString.toCharArray()) {
            if (map.containsKey(ch)) {
                result = Math.min(result, map.get(ch));
            }
            else {
                return 0;
            }

        }
        return result;
    }

}
