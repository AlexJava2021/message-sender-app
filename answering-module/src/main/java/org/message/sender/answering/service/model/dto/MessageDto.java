package org.message.sender.answering.service.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.message.sender.answering.service.model.MessageStatus;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Accessors(fluent = true)
@Schema(description = "Message Dto contains information about message.")
public class MessageDto
{
    @JsonProperty("message")
    private final CreateMessageRequest messageDto;

    @JsonProperty("errors")
    private final String errorDescription;

    @JsonProperty("message_text")
    private final String messageText;

    @JsonProperty("date")
    private final LocalDateTime messagePublishingDate;

    @JsonProperty("status")
    private final MessageStatus messageStatus;

    @JsonProperty("date_status")
    private final LocalDateTime messageStatusDate;
}
