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
@Schema(description = "Dto returned after creating message.")
public class CreateMessageResponse
{
    @JsonProperty("unique_message")
    private final String uniqueMessageIdentifier;
    @JsonProperty("status")
    private final MessageStatus messageStatus;
    @JsonProperty("date")
    private final LocalDateTime messagePublishingDate;
}
