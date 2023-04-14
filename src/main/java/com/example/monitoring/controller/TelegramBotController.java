package com.example.monitoring.controller;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class TelegramBotController extends TelegramLongPollingBot {
    private final String botToken = "YOUR_BOT_TOKEN";
    private final String botUsername = "YOUR_BOT_USERNAME";
    private final Map<Long, Set<String>> userSources = new ConcurrentHashMap<>();
    private final Map<Long, Boolean> monitoringStatus = new ConcurrentHashMap<>();
    private final CommandFactory commandFactory = new CommandFactory();


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
            } else if (text.equals("/stop")) {
                try {
                    Command stopMonitoringCommand = new StopMonitoringCommand(this);
                    stopMonitoringCommand.execute(chatId, null);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (text.startsWith("/add ")) {
                try {
                    Command addSourceCommand = new AddSourceCommand(this);
                    addSourceCommand.execute(chatId, text.substring(5));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (text.startsWith("/remove ")) {
                try {
                    Command removeSourceCommand = new RemoveSourceCommand(this);
                    removeSourceCommand.execute(chatId, text.substring(8));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (text.equals("/list")) {
                try {
                    Command listSourcesCommand = new CheckListSourcesCommand(this);
                    listSourcesCommand.execute(chatId, null);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else if (text.equals("/start")) {
                try {
                    Command startMonitoringCommand = new StartMonitoringCommand(this);
                    startMonitoringCommand.execute(chatId, null);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else {
                sendReply(chatId, "Неизвестная команда: " + text);
            }
        }
    }

    void startMonitoring(long chatId) throws TelegramApiException {
        monitoringStatus.put(chatId, true);
        sendReply(chatId, "Мониторинг начат.");
    }

    void stopMonitoring(long chatId) throws TelegramApiException {
        monitoringStatus.put(chatId, false);
        sendReply(chatId, "Мониторинг остановлен.");
    }

    void addSource(long chatId, String source) throws TelegramApiException {
        userSources.computeIfAbsent(chatId, k -> new HashSet<>()).add(source);
        sendReply(chatId, "Источник добавлен успешно: " + source);
    }

    void removeSource(long chatId, String source) throws TelegramApiException {
        Set<String> sources = userSources.get(chatId);
        if (sources != null && sources.remove(source)) {
            sendReply(chatId, "Источник удален успешно: " + source);
        } else {
            sendReply(chatId, "Не удалось найти источник: " + source);
        }
    }

    void listSources(long chatId) throws TelegramApiException {
        Set<String> sources = userSources.get(chatId);
        if (sources == null || sources.isEmpty()) {
            sendReply(chatId, "У вас нет добавленных источников.");
        } else {
            String response = "Ваши источники:\n" + String.join("\n", sources);
            sendReply(chatId, response);
        }
    }


    private void sendReply(long chatId, String text) {
        SendMessage message = new SendMessage()
                .setChatId(Long.toString(chatId))
                .setText(text);
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