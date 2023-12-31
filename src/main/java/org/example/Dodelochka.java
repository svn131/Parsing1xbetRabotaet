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

public class Dodelochka {
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
                    printJsonStructure(jsonObject, "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Failed to get server response");
            }

            con.disconnect();

            System.out.println("Hello world!");

            int min = 5000;
            int max = 10000;

            Random random = new Random();
            int randomNumber = random.nextInt(max - min + 1) + min;

            Thread.sleep(randomNumber);
        }
    }

    private static void printJsonStructure(JSONObject jsonObject, String indent) throws JSONException {
        for (String key : jsonObject.keySet()) {
            if (key.equals("O1") || key.equals("O2")) {
                System.out.println("Значение ключа '" + key + "': " + jsonObject.get(key));
            } else if (key.equals("PS")) {
                JSONArray psArray = jsonObject.getJSONArray(key);
                System.out.println("Значения ключа 'PS' (первая четверть):");

                for (int i = 0; i < psArray.length(); i++) {
                    JSONObject psObject = psArray.getJSONObject(i);
                    JSONObject valueObject = psObject.getJSONObject("Value");

                    // Добавьте код вывода времени
                    String time = valueObject.optString("T", "");
                    if (!time.isEmpty()) {
                        System.out.println("Время: " + time);
                    }

                    String nfValue = valueObject.optString("NF", "");
                    int s1Value = valueObject.optInt("S1", -1);
                    int s2Value = valueObject.optInt("S2", -1);

                    if (!nfValue.isEmpty() && s1Value != -1 && s2Value != -1) {
                        if (nfValue.equals("1-я Четверть")) {
                            System.out.println("Значение S1: " + s1Value);
                            System.out.println("Значение S2: " + s2Value);
                        }
                    } else {
                        System.out.println("Не удалось получить значения S1 и S2 для первой четверти.");
                    }
                }
            } else if (key.equals("TS")) {
                int tsValue = jsonObject.getInt(key);
                System.out.println("Значение ключа 'TS': " + tsValue );

//                int tsValue = jsonObject.getInt(key);
//                int minutes = tsValue / 60;
//                int seconds = tsValue % 60;
//                String formattedTime = String.format("%02d:%02d", minutes, seconds);
//                System.out.println("Значение ключа 'TS': " + formattedTime);


            }

            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                printJsonStructure((JSONObject) value, indent + "  ");
            } else if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                for (int i = 0; i < jsonArray.length(); i++) {
                    Object arrayValue = jsonArray.get(i);
                    if (arrayValue instanceof JSONObject) {
                        printJsonStructure((JSONObject) arrayValue, indent + "    ");
                    }
                }
            }
        }
    }




}
// @todo там где ищзвестно что значение ключа не являеться масивом джейсонов а только самим значением можно убрать проверку на являеться ли оно масивом джейсонов