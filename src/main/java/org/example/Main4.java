package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main4 {
    public static void main(String[] args) throws IOException {
        String url = "https://1xstavka.ru/LiveFeed/Get1x2_VZip";
        String queryString = "sports=3&count=50&antisports=188&mode=4&country=1&partner=51&getEmpty=true&noFilterBlockEvent=true";

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
        } else {
            System.out.println("Ошибка получения ответа от сервера");
        }

        // Закрытие соединения
        con.disconnect();
    }
}
