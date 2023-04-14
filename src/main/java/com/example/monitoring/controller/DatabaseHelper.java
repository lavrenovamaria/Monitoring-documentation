package com.example.monitoring.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String DATABASE_URL = "jdbc:sqlite:monitoring.db";

    public DatabaseHelper() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            connection.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS user_schedules (chat_id INTEGER PRIMARY KEY, schedule TEXT)"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setSchedule(long chatId, String schedule) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT OR REPLACE INTO user_schedules (chat_id, schedule) VALUES (?, ?)"
             )) {
            statement.setLong(1, chatId);
            statement.setString(2, schedule);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getSchedule(long chatId) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT schedule FROM user_schedules WHERE chat_id = ?"
             )) {
            statement.setLong(1, chatId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("schedule");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
