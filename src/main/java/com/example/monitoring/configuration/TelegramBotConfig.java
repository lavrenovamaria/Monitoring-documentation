//package com.example.monitoring.configuration;
//
//import com.example.monitoring.controller.MyTelegramBot;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.telegram.telegrambots.bots.DefaultBotOptions;
//
//@Configuration
//public class TelegramBotConfig {
//
//    @Value("${bot.token}")
//    private String botToken;
//
//    @Value("${bot.username}")
//    private String botUsername;
//
//    @Bean
//    public MyTelegramBot myTelegramBot() {
//        DefaultBotOptions options = new DefaultBotOptions();
//        return new MyTelegramBot(options, botToken, botUsername);
//    }
//}
