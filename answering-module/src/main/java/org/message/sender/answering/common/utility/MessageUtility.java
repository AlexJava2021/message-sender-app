package org.message.sender.answering.common.utility;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.message.sender.answering.repository.MessageRepository;
import org.message.sender.answering.service.model.MessageStatus;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class MessageUtility {

    private final MessageRepository messageRepository;

    @Transactional
    public void updateMessageOnSuccessOrError(String messageIdentifier, MessageStatus status, String text)
    {
        if (MessageStatus.ERROR.equals(status))
        {
            messageRepository.updateMessageOnError(
                    MessageStatus.ERROR,
                    text,
                    Timestamp.from(Instant.now()),
                    messageIdentifier
            );
        }
        else if (MessageStatus.SENT.equals(status))
        {
            messageRepository.updateMessageOnSuccess(
                    MessageStatus.SENT,
                    text,
                    Timestamp.from(Instant.now()),
                    messageIdentifier
            );
        }
    }
}
