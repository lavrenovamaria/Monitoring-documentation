package com.example.monitoring.controller;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StopMonitoringCommand implements Command {
    private final TelegramBotController telegramBotController;

    public StopMonitoringCommand(TelegramBotController telegramBotController) {
        this.telegramBotController = telegramBotController;
    }

    @Override
    public void execute(long chatId, String args) throws TelegramApiException {
        telegramBotController.stopMonitoring(chatId);
    }
}