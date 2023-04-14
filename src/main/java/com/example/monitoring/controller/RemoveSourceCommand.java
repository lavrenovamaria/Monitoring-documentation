package com.example.monitoring.controller;

import com.example.monitoring.service.MonitoringService;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class RemoveSourceCommand implements Command {
    private final MonitoringService telegramBotController;

    public RemoveSourceCommand(MonitoringService telegramBotController) {
        this.telegramBotController = telegramBotController;
    }

    @Override
    public void execute(long chatId, String args) throws TelegramApiException {
        telegramBotController.removeSource(chatId, args);
    }
}