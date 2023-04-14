package com.example.monitoring.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UniversalParser {
    public List<String> parse(String sourceUrl, String interest) {
        List<String> results = new ArrayList<>();

        try {
            Document document = Jsoup.connect(sourceUrl).get();
            Elements elements = document.body().select("*:contains(" + interest + ")");

            for (Element element : elements) {
                results.add(element.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    public static String getContent(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            return doc.outerHtml();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
