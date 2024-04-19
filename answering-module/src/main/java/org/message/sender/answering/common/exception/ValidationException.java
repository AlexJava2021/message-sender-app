package org.message.sender.answering.common.exception;

public class ValidationException extends RuntimeException
{
    public ValidationException(String errorMessage)
    {
        super(errorMessage);
    }
}
