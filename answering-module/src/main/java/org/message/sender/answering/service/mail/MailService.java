package org.message.sender.answering.service.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.message.sender.answering.service.model.Mail;
import org.message.sender.answering.service.model.MailMessage;
import org.message.sender.answering.common.client.MessageBrokerClient;
import org.message.sender.answering.service.model.MessageStatus;
import org.message.sender.answering.service.model.MessageStatusInfo;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableRabbit
@RequiredArgsConstructor
public class MailService
{
    private final MessageBrokerClient messageBrokerClient;
    @RabbitListener(queues = "mail_queue")
    public void processMailQueue(MailMessage message) {
        MessageStatusInfo messageStatusInfo;
        try {
            sendMailToSMTP(message);
            log.info("Mail with the text: {} got sent.", message.getMailText());
            messageStatusInfo = new MessageStatusInfo(
                    message.getUniqueMessageIdentifier(),
                    MessageStatus.SENT,
                    message.getMailText()
            );
        }
        catch (Exception exc)
        {
            log.error("MailService#processMailQueue throws Exception {}.", message.getMailText());
            messageStatusInfo = new MessageStatusInfo(
                    message.getUniqueMessageIdentifier(),
                    MessageStatus.ERROR,
                    exc.getMessage()
            );
        }
        publishMessageStatusInfo(messageStatusInfo);
        log.info(
                "MessageStatusInfo with identifier: {} got published to status_queue.",
                message.getUniqueMessageIdentifier()
        );
    }

    private void publishMessageStatusInfo(MessageStatusInfo statusInfo)
    {
        messageBrokerClient.sendMessageToStatusQueue(statusInfo);
    }

    private void sendMailToSMTP(MailMessage mailMessage)
    {
        try {
            Mail mail = new Mail(mailMessage.getMailText(), new byte[]{12, 34, 56, 78}, "pdf");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
