package com.example.monitoring.controller;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class RemoveSourceCommand implements Command {
    private final TelegramBotController telegramBotController;

    public RemoveSourceCommand(TelegramBotController telegramBotController) {
        this.telegramBotController = telegramBotController;
    }

    @Override
    public void execute(long chatId, String args) throws TelegramApiException {
        telegramBotController.removeSource(chatId, args);
    }
}