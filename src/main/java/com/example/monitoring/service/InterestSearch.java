package com.example.monitoring.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InterestSearch {

    public List<String> findMatchingEvents(String content, List<String> interests) {
        List<String> matchingEvents = new ArrayList<>();
        Document doc = Jsoup.parse(content);

        for (String interest : interests) {
            Elements elements = doc.select(":contains(" + interest + ")");

            for (Element element : elements) {
                String eventText = element.text();
                if (!matchingEvents.contains(eventText)) {
                    matchingEvents.add(eventText);
                }
            }
        }

        return matchingEvents;
    }
}
