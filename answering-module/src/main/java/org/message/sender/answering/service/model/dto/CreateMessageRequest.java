package org.message.sender.answering.service.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Getter
@Builder(toBuilder = true)
@Jacksonized
@Schema(description = "Message Dto to create new message.")
public class CreateMessageRequest
{
    @JsonProperty("unique_message")
    private final String uniqueMessageIdentifier;

    @JsonProperty("group_users")
    private final int groupUsers;

    @JsonProperty("template_id")
    private long messageTemplateId;

    @JsonProperty("file")
    private byte[] attachedFileContent;

    @JsonProperty("type_file")
    private String attachedFileType;

    @JsonProperty("data")
    private Map<String, String> dataReplacements;
}
