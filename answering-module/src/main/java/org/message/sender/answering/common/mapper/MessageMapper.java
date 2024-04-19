package org.message.sender.answering.common.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.message.sender.answering.repository.entity.MessageEntity;
import org.message.sender.answering.service.model.Message;
import org.message.sender.answering.service.model.dto.CreateMessageRequest;
import org.message.sender.answering.service.model.dto.CreateMessageResponse;
import org.message.sender.answering.service.model.dto.MessageDto;

import java.sql.Timestamp;
import java.time.Instant;

@Mapper(componentModel = "spring")
public interface MessageMapper
{
    Message messageDtoToMessage(CreateMessageRequest messageDto);

    @Mapping(target = "messageDto.uniqueMessageIdentifier", source = "uniqueMessageIdentifier")
    @Mapping(target = "messageDto.groupUsers", source = "groupUsers")
    @Mapping(target = "messageDto.messageTemplateId", source = "messageTemplateId")
    @Mapping(target = "messageDto.attachedFileContent", source = "attachedFileContent")
    @Mapping(target = "messageDto.attachedFileType", source = "attachedFileType")
    @Mapping(target = "messageDto.dataReplacements", source = "dataReplacements")
    @Mapping(target = "errorDescription", source = "errors")
    @Mapping(target = "messageText", source = "messageText")
    @Mapping(target = "messagePublishingDate", source = "date")
    @Mapping(target = "messageStatus", source = "status")
    @Mapping(target = "messageStatusDate", source = "dateStatus")
    MessageDto messageEntityToMessageDto(MessageEntity messageEntity);

    @Mapping(target = "status", expression = "java(MessageStatus.UNSENT)")
    @Mapping(target = "date", expression = "java(getTimestamp())")
    @Mapping(target = "dateStatus", expression = "java(getTimestamp())")
    MessageEntity messageDtoToMessageEntity(CreateMessageRequest messageDto);

    default Timestamp getTimestamp()
    {
        return Timestamp.from(Instant.now());
    }
}
