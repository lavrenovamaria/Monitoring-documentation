package com.example.monitoring.configuration;

import com.example.monitoring.telegram.MyTelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.bots.DefaultBotOptions;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TelegramBotConfigTest {

    @Value("${bot.token}")
    private String botToken;

    @Value("${bot.username}")
    private String botUsername;

    private TelegramBotConfig telegramBotConfig;

    @BeforeEach
    public void setUp() {
        telegramBotConfig = new TelegramBotConfig();
    }

    @Test
    public void myTelegramBotBeanCreationTest() {
        MyTelegramBot myTelegramBot = telegramBotConfig.myTelegramBot();
        assertNotNull(myTelegramBot);
    }
}
