package com.example.monitoring.controller;

import java.sql.*;

public class SQLiteDatabase implements Database {
    private static final String DB_URL = "jdbc:sqlite:identifier.sqlite";

    public SQLiteDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            String createTableSql = "CREATE TABLE IF NOT EXISTS schedules ("
                    + "chat_id INTEGER PRIMARY KEY,"
                    + "schedule TEXT NOT NULL"
                    + ")";
            try (PreparedStatement statement = connection.prepareStatement(createTableSql)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSchedule(long chatId, String schedule) {
        String insertOrUpdateSql = "INSERT OR REPLACE INTO user_schedules (chat_id, schedule) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(insertOrUpdateSql)) {
            statement.setLong(1, chatId);
            statement.setString(2, schedule);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getSchedule(long chatId) {
        String selectSql = "SELECT schedule FROM user_schedules WHERE chat_id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(selectSql)) {
            statement.setLong(1, chatId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("schedule");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Реализуйте другие методы для работы с данными мониторинга, если это необходимо
}
