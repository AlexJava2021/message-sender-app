package org.message.sender.answering.common.validation.impl;

import org.message.sender.answering.common.cache.impl.MailTemplateCache;
import org.message.sender.answering.common.exception.ValidationException;
import org.message.sender.answering.repository.MessageRepository;
import org.message.sender.answering.service.model.Message;
import org.message.sender.answering.common.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageSettingValidator implements Validator<Message>
{
    private static final String GROUP_USER_IS_INVALID = "Group user is invalid.";

    private static final String MAIL_TEMPLATE_IS_NOT_FOUND = "Mail template is not found.";

    private static final String FILE_TYPE_IS_INVALID = "File type is invalid.";

    private static final List<Integer> GROUP_USER_LIST = new ArrayList<>();

    private static final List<String> FILE_TYPE_LIST = new ArrayList<>();

    private final MessageRepository messageRepository;

    private final MailTemplateCache mailTemplateCache;

    public MessageSettingValidator(MessageRepository messageRepository, MailTemplateCache mailTemplateCache)
    {
        this.messageRepository = messageRepository;
        this.mailTemplateCache = mailTemplateCache;
        initMaps();
    }

    @Override
    public void validate(Message message) {
        checkGroupUser(message.groupUsers());
        checkFileType(message.attachedFileType());
        checkMailTemplateId(message.messageTemplateId());
    }

    private void initMaps()
    {
        if (GROUP_USER_LIST.isEmpty())
        {
            GROUP_USER_LIST.addAll(messageRepository.findAllGroupUser());
        }
        if (FILE_TYPE_LIST.isEmpty())
        {
            FILE_TYPE_LIST.addAll(messageRepository.findAllFileTypes());
        }
    }

    private void checkGroupUser(int groupUser)
    {
        if (!GROUP_USER_LIST.contains(groupUser))
        {
            throw new ValidationException(GROUP_USER_IS_INVALID);
        }
    }

    private void checkMailTemplateId(long templateId)
    {
        if (mailTemplateCache.getItem(templateId).isEmpty())
        {
            throw new ValidationException(MAIL_TEMPLATE_IS_NOT_FOUND);
        }
    }

    private void checkFileType(String fileType)
    {
        if (!FILE_TYPE_LIST.contains(fileType))
        {
            throw new ValidationException(FILE_TYPE_IS_INVALID);
        }
    }
}
