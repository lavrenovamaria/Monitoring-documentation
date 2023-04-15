package com.example.monitoring.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public interface Database {
    void setSchedule(long chatId, String schedule);
    String getSchedule(long chatId);
    // другие методы для работы с данными мониторинга
}
