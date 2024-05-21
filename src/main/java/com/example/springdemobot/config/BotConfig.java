package com.example.springdemobot.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@Data
@ConfigurationProperties(prefix = "bot")
@EnableScheduling
public class BotConfig {

    @NotBlank
    String name;

    @NotBlank
    String token;

}


