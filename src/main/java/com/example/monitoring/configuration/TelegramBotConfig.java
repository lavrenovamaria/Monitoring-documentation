package com.example.monitoring.configuration;

import com.example.monitoring.controller.MyTelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Configuration
public class TelegramBotConfig {

    @Bean
    public MyTelegramBot myTelegramBot() {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
        return new MyTelegramBot(options, botToken, botUsername);
    }
}
