package org.message.sender.answering.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

@Getter
@RequiredArgsConstructor
@Accessors(fluent = true)
public class Message implements Serializable
{
    private final String uniqueMessageIdentifier;

    private final int groupUsers;

    private final long messageTemplateId;

    private final String attachedFileType;

    private final Map<String, String> dataReplacements;
}
