package com.example.monitoring.controller;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AddSourceCommand implements Command {
    private final TelegramBotController telegramBotController;

    public AddSourceCommand(TelegramBotController telegramBotController) {
        this.telegramBotController = telegramBotController;
    }

    @Override
    public void execute(long chatId, String args) throws TelegramApiException {
        telegramBotController.addSource(chatId, args);
    }
}