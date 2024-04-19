package org.message.sender.answering.service.answering;

import org.message.sender.answering.service.model.dto.CreateMessageRequest;
import org.message.sender.answering.service.model.dto.CreateMessageResponse;
import org.message.sender.answering.service.model.dto.MessageDto;


public interface AnsweringService
{
    CreateMessageResponse publishMessage(CreateMessageRequest messageDto);

    MessageDto getMessage(String uniqueMessageIdentifier);
}
