package org.message.sender.answering.common.validation;

public interface Validator<T>
{
    void validate(T message);
}
