package org.message.sender.answering.service.answering.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.message.sender.answering.common.exception.MessageNotFoundException;
import org.message.sender.answering.common.mapper.MessageMapper;
import org.message.sender.answering.repository.MessageRepository;
import org.message.sender.answering.repository.entity.MessageEntity;
import org.message.sender.answering.service.answering.AnsweringService;
import org.message.sender.answering.common.client.MessageBrokerClient;
import org.message.sender.answering.service.model.Message;
import org.message.sender.answering.service.model.MessageStatus;
import org.message.sender.answering.service.model.dto.CreateMessageRequest;
import org.message.sender.answering.service.model.dto.CreateMessageResponse;
import org.message.sender.answering.service.model.dto.MessageDto;
import org.message.sender.answering.common.utility.MessageUtility;
import org.message.sender.answering.common.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AnsweringServiceImpl implements AnsweringService
{
    private final MessageMapper messageMapper;
    private final Validator<CreateMessageRequest> validator;
    private final MessageRepository messageRepository;
    private final MessageBrokerClient messageBrokerClient;
    private final MessageUtility messageUtility;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreateMessageResponse publishMessage(CreateMessageRequest createMessageRequest) {
        final String messageIdentifier = createMessageRequest.getUniqueMessageIdentifier();
        validator.validate(createMessageRequest);
        final MessageEntity messageEntity = messageMapper.messageDtoToMessageEntity(createMessageRequest);
        messageRepository.save(messageEntity);

        try {
            final Message message = messageMapper.messageDtoToMessage(createMessageRequest);
            messageBrokerClient.sendMessageToMessageQueue(message);
            log.info("Message with unique Identifier {} got published to message_queue.", messageIdentifier);
        }
        catch (Exception exc)
        {
            log.error("AnsweringServiceImpl#publishMessage() throws {}", exc);
            messageUtility.updateMessageOnSuccessOrError(
                    createMessageRequest.getUniqueMessageIdentifier(),
                    MessageStatus.ERROR,
                    exc.getMessage()
            );
        }

        MessageDto messageDto = getMessage(messageIdentifier);
        return new CreateMessageResponse(
                messageIdentifier,
                messageDto.messageStatus(),
                messageDto.messagePublishingDate()
        );
    }

    @Override
    public MessageDto getMessage(String uniqueMessageIdentifier) {
        Optional<MessageEntity> optional = messageRepository.findById(uniqueMessageIdentifier);
        if (optional.isEmpty())
        {
            throw new MessageNotFoundException(
                    String.format("Message with unique identifier %s not found.", uniqueMessageIdentifier));
        }

        return messageMapper.messageEntityToMessageDto(optional.get());
    }

}
