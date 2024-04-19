package org.message.sender.answering.common.validation.impl;


import org.apache.commons.lang3.StringUtils;
import org.message.sender.answering.common.exception.ValidationException;
import org.message.sender.answering.service.model.dto.CreateMessageRequest;
import org.message.sender.answering.common.validation.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("createMessageDtoValidator")
public class CreateMessageDtoValidator implements Validator<CreateMessageRequest> {
    private static final String DTO_IS_NULL = "Message DTO must not be Null";
    private static final String MESSAGE_IDENTIFIER_IS_NOT_SET = "Message DTO must not be Null or Empty";
    private static final String MESSAGE_GROUP_USERS_IS_NOT_VALID = "Group user is not valid.";
    private static final String MESSAGE_TEMPLATE_ID_IS_NOT_VALID = "Message template id is not valid.";

    @Override
    public void validate(CreateMessageRequest messageDto) {
        if (messageDto == null)
        {
            throw new ValidationException(DTO_IS_NULL);
        }
        if (StringUtils.isEmpty(messageDto.getUniqueMessageIdentifier()))
        {
            throw new ValidationException(MESSAGE_IDENTIFIER_IS_NOT_SET);
        }
        if (messageDto.getGroupUsers() <= 0)
        {
            throw new ValidationException(MESSAGE_GROUP_USERS_IS_NOT_VALID);
        }
        if (messageDto.getMessageTemplateId() <= 0)
        {
            throw new ValidationException(MESSAGE_TEMPLATE_ID_IS_NOT_VALID);
        }
    }
}
