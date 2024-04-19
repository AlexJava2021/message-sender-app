package org.message.sender.answering.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
@Accessors(fluent = true)
public class MessageMail implements Serializable
{
    private final String uniqueMessageIdentifier;

    private final String messageContent;
}
