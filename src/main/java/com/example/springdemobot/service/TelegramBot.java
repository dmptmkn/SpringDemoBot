package com.example.springdemobot.service;

import com.example.springdemobot.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        String firstName = update.getMessage().getChat().getFirstName();

        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();

            switch (text) {
                case "/start" -> startCommandReceived(chatId, firstName);
                default -> sendMessage(chatId, "Sorry, command wasn't recognized!");
            }
        }
    }

    private void startCommandReceived(long chatId, String firstName) {
        String answer = "Hi, " + firstName + ", nice to meet you!";
        sendMessage(chatId, answer);
        log.info("Replied to user " + firstName);
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error: " + e.getMessage());
        }
    }
}
