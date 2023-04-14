package com.example.monitoring;

import com.example.monitoring.SourceMonitoringManager;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<String> sources = Arrays.asList("https://example1.com", "https://example2.com");
        SourceMonitoringManager monitoringManager = new SourceMonitoringManager();
        // Ваш код для запуска приложения (например, запуск веб-сервера или телеграм-бота)
    }
}
