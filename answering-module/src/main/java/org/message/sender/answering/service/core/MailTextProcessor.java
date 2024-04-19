package org.message.sender.answering.service.core;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MailTextProcessor {

    public String process(String templateText, Map<String, String> tagReplacements)
    {
        for (Map.Entry<String, String> entry : tagReplacements.entrySet())
        {
            templateText = templateText.replace(entry.getKey(), entry.getValue());
        }
        return templateText;
    }
}
