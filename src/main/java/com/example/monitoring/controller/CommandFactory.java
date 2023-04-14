package com.example.monitoring.controller;

import com.example.monitoring.service.MonitoringService;

public class CommandFactory {
    private final MonitoringService monitoringService;

    public CommandFactory() {
        this.monitoringService = monitoringService;
    }

    public Command getCommand(String text) {
        if (text.startsWith("/start")) {
            return new StartMonitoringCommand(monitoringService);
        } else if (text.startsWith("/stop")) {
            return new StopMonitoringCommand(monitoringService);
        } else if (text.startsWith("/add ")) {
            return new AddSourceCommand(monitoringService);
        } else if (text.startsWith("/remove ")) {
            return new RemoveSourceCommand(monitoringService);
        } else if (text.equals("/list")) {
            return new CheckListSourcesCommand(monitoringService);
        } else {
            return null;
        }
    }
}