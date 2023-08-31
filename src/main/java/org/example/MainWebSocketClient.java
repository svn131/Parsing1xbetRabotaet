//package org.example;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//
//import javax.websocket.*;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.Random;
//
//@ClientEndpoint
//public class MainWebSocketClient {
//    private Session session;
//
//    @OnOpen
//    public void onOpen(Session session) {
//        System.out.println("WebSocket connection opened");
//        this.session = session;
//    }
//
//    @OnClose
//    public void onClose() {
//        System.out.println("WebSocket connection closed");
//    }
//
////    @OnError
////    public void onError(Throwable throwable) {
////        throwable.printStackTrace();
////    }
//
//    @OnError
//    public void onError(Throwable throwable) {
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        throwable.printStackTrace(pw);
//        String stackTrace = sw.toString();
//        System.out.println(stackTrace);
//    }
//
//
//    @OnMessage
//    public void onMessage(String message) {
//        // Обработка полученных обновлений данных
//        System.out.println("Received message: " + message);
//        parseAndProcessData(message); // Вызываем метод для парсинга и обработки данных
//    }
//
//    public void start() {
//        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
//
//
//
//        container.setDefaultMaxSessionIdleTimeout(5000); // Установите необходимые настройки WebSocket контейнера
//
//
//
////        String uri = "https://1xstavka.ru/live/basketball"; // Замените на соответствующий URL вашего WebSocket сервера
//        String uri = "ws://1xstavka.ru/live/basketball";
//
//
//
//        try {
//            container.connectToServer(this, new URI(uri));
//        } catch (IOException | DeploymentException | URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void parseAndProcessData(String data) {
//        // Используйте Jsoup для парсинга и обработки данных
//        Document document = Jsoup.parse(data);
//        Elements titleElement = document.select(".c-events-scoreboard__line");
//        Elements titleElement1 = document.select(".c-events__teams");
//
//        for (var element : titleElement) {
//            System.out.println(element.text());
//        }
//
//        for (var element : titleElement1) {
//            System.out.println(element.text());
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException, IOException {
//        MainWebSocketClient client = new MainWebSocketClient();
//        client.start();
//
//        while (true) {
//            System.out.println("Hello world!");
//
//            int min = 10000;
//            int max = 20000;
//
//            Random random = new Random();
//            int randomNumber = random.nextInt(max - min + 1) + min;
//
//            Thread.sleep(randomNumber);
//
//            // Выполните требуемые операции, если нужно
//
//        }
//    }
//}
//
