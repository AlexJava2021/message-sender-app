ALTER TABLE message
    ADD CONSTRAINT fk_message_group_user FOREIGN KEY (group_user) REFERENCES group_user (ugroup);

ALTER TABLE message
    ADD CONSTRAINT fk_message_mail_template FOREIGN KEY (template_id) REFERENCES mail_template (id);

