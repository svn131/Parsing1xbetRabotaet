package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        while (true) {

            int min = 60000;
            int max = 120000;

            Random random = new Random();
            int randomNumber = random.nextInt(max - min + 1) + min;

            Thread.sleep(randomNumber);

            System.out.println("Hello world!");


            try {


                var document = Jsoup.connect("https://1xstavka.ru/live/basketball").get();
                var document1 = Jsoup.connect("https://1xstavka.ru/live/basketball").get();


//            var titleElement =  document.select(".c-events-scoreboard__cell");
                var titleElement = document.select(".c-events-scoreboard__line");
                var titleElement1 = document1.select(".c-events__teams");


                for (var element : titleElement) {
                    System.out.println(element.text());
                }


                for (var element : titleElement1) {
                    System.out.println(element.text());
                }



                // Очистить объекты document и document1
                document = null;
                document1 = null;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }
}





//            var titleElement =  document.select("#some-id");


////        var titleElement =  document.selectFirst("title");
//        var titleElement =  document.select("h3");




//            var titleElement =  document.select("a");


//            <div class="c-events-scoreboard__lines"><div class="c-events-scoreboard__line"><span class="c-events-scoreboard__ball"><div class="c-events-scoreboard__icon" style="display: none;"><div class="sport_icons sport_icon_3"></div></div></span> <!----> <span class="c-events-scoreboard__cell c-events-scoreboard__cell--all">38</span> <span title="1-я Четверть" class="c-events-scoreboard__cell">29</span><span title="2-я Четверть" class="c-events-scoreboard__cell">9</span></div> <div class="c-events-scoreboard__line"><span class="c-events-scoreboard__ball"><div class="c-events-scoreboard__icon" style="display: none;"><div class="sport_icons sport_icon_3"></div></div></span> <!----> <span class="c-events-scoreboard__cell c-events-scoreboard__cell--all">33</span> <span title="1-я Четверть" class="c-events-scoreboard__cell">23</span><span title="2-я Четверть" class="c-events-scoreboard__cell">10</span></div></div>




//            for (var element:titleElement){
//                System.out.println(element.attr("href"));
////                System.out.println(element.attr("class"));
//            }

//            System.out.println(titleElement.text());


//<div class="c-events-scoreboard__line"><span class="c-events-scoreboard__ball"><div class="c-events-scoreboard__icon" style="display: none;"><div class="sport_icons sport_icon_3"></div></div></span> <!----> <span class="c-events-scoreboard__cell c-events-scoreboard__cell--all">55</span> <span title="1-я Четверть" class="c-events-scoreboard__cell">29</span><span title="2-я Четверть" class="c-events-scoreboard__cell">26</span></div>