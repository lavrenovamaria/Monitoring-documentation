package com.example.monitoring.controller;

public interface Database {
    void setSchedule(long chatId, String schedule);
    String getSchedule(long chatId);
    // другие методы для работы с данными мониторинга
}
