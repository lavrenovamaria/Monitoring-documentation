package com.example.monitoring.controller;

import com.example.monitoring.service.MonitoringService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBotController extends TelegramLongPollingBot {
    private final String botToken = "YOUR_BOT_TOKEN";
    private final String botUsername = "YOUR_BOT_USERNAME";

    private final CommandFactory commandFactory = new CommandFactory();
    private final MonitoringService monitoringService = new MonitoringService();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String text = message.getText();
            long chatId = message.getChatId();

            Command command = commandFactory.getCommand(text);
            if (command != null) {
                try {
                    command.execute(chatId, text);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else {
                sendReply(chatId, "Неизвестная команда: " + text);
            }
        }
    }

    private void sendReply(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(Long.toString(chatId));
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
