package org.message.sender.answering.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


public class RabbitMQProperty
{
    private String username;
    private String password;
    private String port;
    private String messageQueue;
    private String mailQueue;
    private String messageStatusQueue;
}
