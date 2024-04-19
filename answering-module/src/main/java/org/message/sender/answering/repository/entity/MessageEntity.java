package org.message.sender.answering.repository.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;
import org.message.sender.answering.repository.DataReplacementAttributeConverter;
import org.message.sender.answering.service.model.MessageStatus;

import java.sql.Timestamp;
import java.util.Map;

@Entity
@Table(name = "message")
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MessageEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private final String uniqueMessageIdentifier;

    @Column(name = "group_user", nullable = false, updatable = false)
    private final int groupUsers;

    @Column(name = "template_id", nullable = false, updatable = false)
    private final long messageTemplateId;

    @Column(name = "file_content", updatable = false)
    private final byte[] attachedFileContent;

    @Column(name = "file_type", updatable = false)
    private final String attachedFileType;

    @Convert(converter = DataReplacementAttributeConverter.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "data_replacement")
    private final Map<String, String> dataReplacements;

    @Column(name = "error_text")
    private final String errors;

    @Column(name = "message_text")
    private final String messageText;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp(source = SourceType.DB)
    private final Timestamp date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private final MessageStatus status;

    @Column(name = "date_status", nullable = false)
    @UpdateTimestamp(source = SourceType.DB)
    private final Timestamp dateStatus;
}
