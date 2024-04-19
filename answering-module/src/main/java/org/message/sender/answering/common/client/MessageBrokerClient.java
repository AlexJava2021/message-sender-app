package org.message.sender.answering.common.client;

import org.message.sender.answering.service.model.MailMessage;
import org.message.sender.answering.service.model.Message;
import org.message.sender.answering.service.model.MessageStatusInfo;

public interface MessageBrokerClient
{
    void sendMessageToMessageQueue(Message message);

    void sendMessageToMailQueue(MailMessage message);

    void sendMessageToStatusQueue(MessageStatusInfo message);
}
