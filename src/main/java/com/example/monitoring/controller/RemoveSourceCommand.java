package com.example.monitoring.controller;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class RemoveSourceCommand implements Command{
    @Override
    public void execute(long chatId, String args) throws TelegramApiException {
        // Ваш код для удаления источника
    }
}
