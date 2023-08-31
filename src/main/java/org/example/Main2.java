package org.example;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main2 {
    public static void main(String[] args) throws IOException, ParseException {
        String url = "https://1xstavka.ru/live/basketball"; // Замените на ваш URL API

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = client.execute(request);

        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity);

        System.out.println(json);

        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) parser.parse(json);

        // Обрабатывайте данные в переменной 'data' и обновляйте содержимое страницы

        response.close();
        client.close();
    }
}
