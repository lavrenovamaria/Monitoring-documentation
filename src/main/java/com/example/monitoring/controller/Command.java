package com.example.monitoring.controller;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Command {
    void execute(long chatId, String args) throws TelegramApiException;
}
