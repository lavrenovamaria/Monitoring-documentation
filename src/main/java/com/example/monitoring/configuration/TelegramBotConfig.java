package com.example.monitoring.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Configuration
public class TelegramBotConfig {

    @Value("${bot.token}")
    private String botToken;

    @Value("${bot.username}")
    private String botUsername;

    @Bean
    public MyTelegramBot myTelegramBot() {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
        return new MyTelegramBot(options, botToken, botUsername);
    }
}
