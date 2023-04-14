package com.example.monitoring.service;

import com.example.monitoring.parser.UniversalParser;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MonitoringService {
    private final Map<Long, Set<String>> userSources = new ConcurrentHashMap<>();
    private final Map<Long, Set<String>> userInterests = new ConcurrentHashMap<>();
    private final UniversalParser parser = new UniversalParser();

    public void checkSourcesForUser(long chatId) {
        Set<String> sources = userSources.get(chatId);
        Set<String> interests = userInterests.get(chatId);

        for (String source : sources) {
            for (String interest : interests) {
                List<String> matchingContent = parser.parse(source, interest);

                // Отправьте уведомления о подходящем контенте пользователю
            }
        }
    }

    public void listSources(long chatId) {
    }

    // Добавьте соответствующие методы для работы с userSources и userInterests
    // Например, методы для добавления, удаления и просмотра источников и интересов
}
