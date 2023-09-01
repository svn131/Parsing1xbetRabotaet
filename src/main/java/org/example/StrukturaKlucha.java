package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class StrukturaKlucha {
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://1xstavka.ru/LiveFeed/Get1x2_VZip";
        String queryString = "sports=3&count=50&antisports=188&mode=4&country=1&partner=51&getEmpty=true&noFilterBlockEvent=true";

        while (true) {
            URL obj = new URL(url + "?" + queryString);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set request method and headers
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            // Get the server response
            int responseCode = con.getResponseCode();
            System.out.println("Server Response: " + responseCode);

            // Read the server response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = con.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    printJsonStructure(jsonObject, "", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Failed to get server response");
            }

            con.disconnect();

            System.out.println("Hello world!");

            int min = 500000;
            int max = 1000000;

            Random random = new Random();
            int randomNumber = random.nextInt(max - min + 1) + min;

            Thread.sleep(randomNumber);
        }
    }

    private static void printJsonStructure(JSONObject jsonObject, String indent, String parent) throws JSONException {
        for (String key : jsonObject.keySet()) {
            if (key.equals("O1")) {
                System.out.println("Найден объект с ключом 'O2':");
                System.out.println("Иерархия вверх:");
                System.out.println("  - " + parent);
                printParentHierarchy(jsonObject, parent);
                return;
            }

            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                printJsonStructure((JSONObject) value, indent + "  ", key);
            } else if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                for (int i = 0; i < jsonArray.length(); i++) {
                    Object arrayValue = jsonArray.get(i);
                    if (arrayValue instanceof JSONObject) {
                        printJsonStructure((JSONObject) arrayValue, indent + "    ", key);
                    }
                }
            }
        }
    }

    private static void printParentHierarchy(JSONObject jsonObject, String parent) throws JSONException {
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                if (((JSONObject) value).has(parent)) {
                    System.out.println("  - " + key);
                    printParentHierarchy((JSONObject) value, key);
                    break;
                } else {
                    printParentHierarchy((JSONObject) value, parent);
                }
            } else if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                for (int i = 0; i < jsonArray.length(); i++) {
                    Object arrayValue = jsonArray.get(i);
                    if (arrayValue instanceof JSONObject) {
                        printParentHierarchy((JSONObject) arrayValue, parent);
                    }
                }
            }
        }
    }

}
