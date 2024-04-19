package org.message.sender.answering.common.client.impl;

import lombok.RequiredArgsConstructor;
import org.message.sender.answering.service.model.MailMessage;
import org.message.sender.answering.common.client.MessageBrokerClient;
import org.message.sender.answering.service.model.Message;
import org.message.sender.answering.service.model.MessageStatusInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQClientImpl implements MessageBrokerClient
{
    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.messageRoutingKey}")
    private String messageRoutingKey;

    @Value("${rabbitmq.mailRoutingKey}")
    private String mailRoutingKey;

    @Value("${rabbitmq.statusRoutingKey}")
    private String statusRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessageToMessageQueue(Message message) {
        rabbitTemplate.convertAndSend(exchange, messageRoutingKey, message);
    }

    @Override
    public void sendMessageToMailQueue(MailMessage message) {
        rabbitTemplate.convertAndSend(exchange, mailRoutingKey, message);
    }

    @Override
    public void sendMessageToStatusQueue(MessageStatusInfo message) {
        rabbitTemplate.convertAndSend(exchange, statusRoutingKey, message);
    }
}
