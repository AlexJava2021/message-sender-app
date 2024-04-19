package org.message.sender.answering.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class MailMessage implements Serializable
{
    private final String uniqueMessageIdentifier;
    private final String mailText;
}
