package com.example.monitoring;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class SourceMonitoringManager {
    private ExecutorService executorService;
    private Map<String, Future<?>> monitoringTasks;

    public SourceMonitoringManager() {
        this.executorService = Executors.newCachedThreadPool();
        this.monitoringTasks = new HashMap<>();
    }

    public void addSource(String source) {
        Runnable monitoringTask = createMonitoringTask(source);
        Future<?> future = executorService.submit(monitoringTask);
        monitoringTasks.put(source, future);
    }

    private static class MonitoringTask {
        private String source;
        private Future<?> future;

        public MonitoringTask(String source, Future<?> future) {
            this.source = source;
            this.future = future;
        }

        public String getSource() {
            return source;
        }

        public Future<?> getFuture() {
            return future;
        }
    }

    public void removeSource(String source) {
        Future<?> future = monitoringTasks.get(source);
        if (future != null) {
            future.cancel(true);
            monitoringTasks.remove(source);
        }
    }

    public List<String> getSources() {
        return new ArrayList<>(monitoringTasks.keySet());
    }

    public void shutdown() {
        executorService.shutdown();
    }



    private Runnable createMonitoringTask(String source) {
        return () -> {
            List<String> changes = monitorSource(source);
            if (!changes.isEmpty()) {
                // Отправить уведомление пользователю
                // sendNotification(chatId, source, changes);
            }
        };
    }

    private boolean contentChanged(String content) {
        // Здесь добавьте код для проверки изменений в содержимом элемента
        // Например, можно хранить предыдущее состояние элемента и сравнивать его с новым
        return false;
    }

    private List<String> monitorSource(String source) {
        List<String> changes = new ArrayList<>();

        try {
            Document document = Jsoup.connect(source).get();
            Elements elements = document.select("div"); // замените "div" на селектор, который соответствует элементам, которые вы хотите отслеживать

            for (Element element : elements) {
                String content = element.text();
                if (contentChanged(content)) {
                    changes.add(content);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return changes;
    }

    public List<String> checkSourceForInterest(String source, String interest) {
        List<String> foundInterests = new ArrayList<>();

        try {
            Document document = Jsoup.connect(source).get();
            Elements elements = document.select("div"); // замените "div" на селектор, который соответствует элементам, содержащим интересы пользователя

            for (Element element : elements) {
                String content = element.text();
                if (content.contains(interest)) {
                    foundInterests.add(content);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return foundInterests;
    }
}

