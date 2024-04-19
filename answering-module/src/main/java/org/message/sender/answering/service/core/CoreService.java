package org.message.sender.answering.service.core;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.message.sender.answering.common.cache.Cache;
import org.message.sender.answering.common.client.MessageBrokerClient;
import org.message.sender.answering.service.model.MailMessage;
import org.message.sender.answering.service.model.Message;
import org.message.sender.answering.service.model.MessageStatus;
import org.message.sender.answering.service.model.MessageStatusInfo;
import org.message.sender.answering.common.utility.MessageUtility;
import org.message.sender.answering.common.validation.Validator;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableRabbit
@RequiredArgsConstructor
public class CoreService
{
    private final Validator<Message> validator;
    private final Cache<Long, String> mailTemplateCache;
    private final MessageBrokerClient messageBrokerClient;
    private final MessageUtility messageUtility;
    private final MailTextProcessor mailTextProcessor;
    @RabbitListener(queues = "message_queue")
    public void processMessageQueue(Message message) {
        try {
            validator.validate(message);
            String templateText = mailTemplateCache.getItem(message.messageTemplateId()).get();
            String mailText = mailTextProcessor.process(templateText, message.dataReplacements());
            MailMessage mailMessage = new MailMessage(message.uniqueMessageIdentifier(), mailText);
            publishMailMessage(mailMessage);
            log.info(
                    "Message with unique Identifier {} got published to mail_queue.",
                    message.uniqueMessageIdentifier()
            );
        }
        catch (Exception exc)
        {
            log.error("CoreService#processMessageQueue throws error: {}", exc.getMessage());
            messageUtility.updateMessageOnSuccessOrError(
                    message.uniqueMessageIdentifier(),
                    MessageStatus.ERROR,
                    exc.getMessage()
            );
        }
    }

    @RabbitListener(queues = "status_queue")
    @Transactional
    public void processMessageStatusInfoQueue(MessageStatusInfo messageStatusInfo) {
        messageUtility.updateMessageOnSuccessOrError(
                messageStatusInfo.uniqueMessageIdentifier(),
                messageStatusInfo.status(),
                messageStatusInfo.text()
        );
    }

    private void publishMailMessage(MailMessage mailMessage)
    {
        messageBrokerClient.sendMessageToMailQueue(mailMessage);
    }
}
