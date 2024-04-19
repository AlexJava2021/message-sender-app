package org.message.sender.answering.common.cache.impl;

import org.message.sender.answering.common.cache.Cache;
import org.message.sender.answering.repository.MessageRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MailTemplateCache implements Cache<Long, String>
{
    private static final Map<Long, Optional<String>> MAIL_TEMPLATE_MAP = new ConcurrentHashMap<>();

    private final MessageRepository messageRepository;

    public MailTemplateCache(MessageRepository messageRepository)
    {
        this.messageRepository = messageRepository;
    }
    @Override
    public Optional<String> getItem(Long key) {
        Optional<String> templateOptional = MAIL_TEMPLATE_MAP.getOrDefault(key, Optional.empty());

        if (templateOptional.isEmpty())
        {
            templateOptional = messageRepository.findMailTemplateById(key);

            if (templateOptional.isEmpty())
            {
                return Optional.empty();
            }

            MAIL_TEMPLATE_MAP.put(key, templateOptional);
        }

        return templateOptional;
    }
}
