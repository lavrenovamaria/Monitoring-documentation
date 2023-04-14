package com.example.monitoring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;

@Configuration
public class TelegramBotConfig {

    @Bean
    public DefaultBotOptions defaultBotOptions() {
        return ApiContext.getInstance(DefaultBotOptions.class);
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(BotSession botSession) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(botSession);
        return botsApi;
    }
}
