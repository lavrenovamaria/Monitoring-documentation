package com.example.monitoring.service;

import com.example.monitoring.SourceMonitoringManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class MonitoringService {
    private final SourceMonitoringManager sourceMonitoringManager;
    private final Map<Long, Set<String>> userSources;
    private final Map<Long, Set<String>> userInterests;

    public MonitoringService() {
        this.sourceMonitoringManager = new SourceMonitoringManager();
        this.userSources = new HashMap<>();
        this.userInterests = new HashMap<>();
    }

    public void addSource(long chatId, String source) {
        userSources.computeIfAbsent(chatId, k -> new HashSet<>()).add(source);
        sourceMonitoringManager.addSource(source);
    }

    public void removeSource(long chatId, String source) {
        Set<String> sources = userSources.get(chatId);
        if (sources != null) {
            sources.remove(source);
        }
    }

    public Set<String> listSources(long chatId) {
        return userSources.getOrDefault(chatId, new HashSet<>());
    }

    public void addInterest(long chatId, String interest) {
        userInterests.computeIfAbsent(chatId, k -> new HashSet<>()).add(interest);
    }

    public void removeInterest(long chatId, String interest) {
        Set<String> interests = userInterests.get(chatId);
        if (interests != null) {
            interests.remove(interest);
        }
    }

    public Set<String> listInterests(long chatId) {
        return userInterests.getOrDefault(chatId, new HashSet<>());
    }

    public void checkSourcesForUser(long chatId) {
        Set<String> sources = userSources.get(chatId);
        Set<String> interests = userInterests.get(chatId);

        for (String source : sources) {
            for (String interest : interests) {
                List<String> matchingContent = sourceMonitoringManager.checkSourceForInterest(source, interest);

                // Отправьте уведомления о подходящем контенте пользователю
            }
        }
    }

    public void startMonitoring(long chatId) {
    }

    public void stopMonitoring(long chatId) {
    }
}
