package org.example;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Random;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class Main3 {
//    public static void main(String[] args) throws IOException {
//        String url = "https://1xstavka.ru/LiveUtil/UpdateCoupon";
//
//        // Создание JSON-строки для запроса
//        String payload = "{\"CfView\":0,\"Events\":[{\"GameId\":473214089,\"Type\":10,\"Coef\":2.535,\"Param\":141.5,\"PV\":null,\"PlayerId\":0,\"Kind\":1}],\"Coef\":2.535,\"Expired\":0,\"GameId\":473214089,\"InstrumentId\":0,\"Kind\":1,\"PV\":null,\"Param\":141.5,\"PlayerId\":0,\"Price\":0,\"Seconds\":0,\"Type\":10,\"Lng\":\"ru\",\"NeedUpdateLine\":false,\"Vid\":0,\"group\":44,\"partner\":51}";
//
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//        // Задание метода запроса и установка заголовков
//        con.setRequestMethod("POST");
//        con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//
//        // Разрешить запись данных в тело запроса
//        con.setDoOutput(true);
//
//        // Отправка данных в тело запроса
//        try (OutputStream os = con.getOutputStream()) {
//            byte[] input = payload.getBytes("utf-8");
//            os.write(input, 0, input.length);
//        }
//
//        // Получение ответа от сервера
//        int responseCode = con.getResponseCode();
//        System.out.println("Ответ сервера: " + responseCode);
//
//        // Закрытие соединения
//        con.disconnect();
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main3 {
    public static void main(String[] args) throws IOException {
        String url = "https://1xstavka.ru/LiveUtil/UpdateCoupon";

        // Создание JSON-строки для запроса
        String payload = "{\"CfView\":0,\"Events\":[{\"GameId\":473214089,\"Type\":10,\"Coef\":2.535,\"Param\":141.5,\"PV\":null,\"PlayerId\":0,\"Kind\":1}],\"Coef\":2.535,\"Expired\":0,\"GameId\":473214089,\"InstrumentId\":0,\"Kind\":1,\"PV\":null,\"Param\":141.5,\"PlayerId\":0,\"Price\":0,\"Seconds\":0,\"Type\":10,\"Lng\":\"ru\",\"NeedUpdateLine\":false,\"Vid\":0,\"group\":44,\"partner\":51}";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Задание метода запроса и установка заголовков
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

        // Разрешить запись данных в тело запроса
        con.setDoOutput(true);

        // Отправка данных в тело запроса
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = payload.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

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
        } else {
            System.out.println("Ошибка получения ответа от сервера");
        }

        // Закрытие соединения
        con.disconnect();
    }
}


