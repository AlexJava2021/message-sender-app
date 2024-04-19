package org.message.sender.answering.service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class Mail
{
    private final String mailText;
    private final byte[] fileContent;
    private final String fileType;

    public Mail(String mailText, byte[] fileContent, String fileType)
    {
        this.mailText = mailText;
        this.fileContent = fileContent;
        this.fileType = fileType;
    }
}
