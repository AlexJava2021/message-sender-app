package org.message.sender.answering.common.exception;

public class MessageNotFoundException extends RuntimeException
{
    private static final String DEFAULT_ERROR_TEXT = "Resource not found.";

    public MessageNotFoundException()
    {
        super(DEFAULT_ERROR_TEXT);
    }

    public MessageNotFoundException(String errorText)
    {
        super(errorText);
    }
}
