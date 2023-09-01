package org.example;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Random;
//
//public class Main5 {
//    public static void main(String[] args) throws IOException, InterruptedException {
//        String url = "https://1xstavka.ru/LiveFeed/Get1x2_VZip";
//        String queryString = "sports=3&count=50&antisports=188&mode=4&country=1&partner=51&getEmpty=true&noFilterBlockEvent=true";
//
//        while (true) {
//            URL obj = new URL(url + "?" + queryString);
//            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//            // Задание метода запроса и установка заголовков
//            con.setRequestMethod("GET");
//            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//
//            // Получение ответа от сервера
//
//            int responseCode = con.getResponseCode();
//            System.out.println("Ответ сервера: " + responseCode);
//
//            // Чтение ответа сервера
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                InputStream inputStream = con.getInputStream();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                String line;
//                StringBuilder response = new StringBuilder();
//
//                while ((line = reader.readLine()) != null) {
//                    response.append(line);
//                }
//                reader.close();
//
//                // Вывод ответа сервера
//                System.out.println("Ответ сервера: " + response.toString());
//            } else {
//                System.out.println("Ошибка получения ответа от сервера");
//            }
//
//            // Закрытие соединения
//            con.disconnect();
//
//            System.out.println("Hello world!");
//
//            int min = 500000;
//            int max = 1000000;
//
//            Random random = new Random();
//            int randomNumber = random.nextInt(max - min + 1) + min;
//
//            Thread.sleep(randomNumber);
//        }
//    }
//}
//


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Main5 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://1xstavka.ru/LiveFeed/Get1x2_VZip";
        String queryString = "sports=3&count=50&antisports=188&mode=4&country=1&partner=51&getEmpty=true&noFilterBlockEvent=true";

        while (true) {
            URL obj = new URL(url + "?" + queryString);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Задание метода запроса и установка заголовков
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            // Получение ответа от сервера

            int responseCode = con.getResponseCode();
            System.out.println("Ответ сервера: " + responseCode);

            // Чтение ответа сервера
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = con.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Вывод ответа сервера
                System.out.println("Ответ сервера: " + response.toString());

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    Map<String, List<Object>> keyValueMap = new HashMap<>();
                    populateKeyValueMap("", jsonObject, keyValueMap);

                    for (Map.Entry<String, List<Object>> entry : keyValueMap.entrySet()) {
                        System.out.println(entry.getKey() + " : " + entry.getValue());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Ошибка получения ответа от сервера");
            }

            // Закрытие соединения
            con.disconnect();

            System.out.println("Hello world!");

            int min = 500000;
            int max = 1000000;

            Random random = new Random();
            int randomNumber = random.nextInt(max - min + 1) + min;

            Thread.sleep(randomNumber);
        }
    }

    private static void populateKeyValueMap(String key, Object value, Map<String, List<Object>> keyValueMap) {
        if (value instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) value;
            jsonObject.keys().forEachRemaining(jsonKey -> {
                try {
                    Object jsonValue = jsonObject.get(jsonKey);
                    populateKeyValueMap(key + "." + jsonKey, jsonValue, keyValueMap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        } else if (value instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) value;
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    Object jsonValue = jsonArray.get(i);
                    populateKeyValueMap(key, jsonValue, keyValueMap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (keyValueMap.containsKey(key)) {
                keyValueMap.get(key).add(value);
            } else {
                List<Object> values = new ArrayList<>();
                values.add(value);
                keyValueMap.put(key, values);
            }
        }
    }
}
