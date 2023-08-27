package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        try {


            var document = Jsoup.connect("https://devmark.ru").get();

//        var titleElement =  document.selectFirst("title");
        var titleElement =  document.select("h3");

        for (var element:titleElement){
            System.out.println(element.text());
        }

//            System.out.println(titleElement.text());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}