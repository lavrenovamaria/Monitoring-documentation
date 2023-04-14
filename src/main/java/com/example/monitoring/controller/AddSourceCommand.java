package com.example.monitoring.controller;

import com.example.monitoring.service.MonitoringService;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AddSourceCommand implements Command {
    private final MonitoringService monitoringService;

    public AddSourceCommand(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @Override
    public void execute(long chatId, String args) throws TelegramApiException {
        monitoringService.addSource(chatId, args);
    }
}
