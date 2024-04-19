package org.message.sender.answering.repository;

import org.message.sender.answering.repository.entity.MessageEntity;
import org.message.sender.answering.service.model.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<MessageEntity, String> {
    @Modifying
    @Query("update MessageEntity m set m.status = :status, m.errors = :error, m.dateStatus = :date " +
            "where m.uniqueMessageIdentifier = :id")
    void updateMessageOnError(
            @Param("status") MessageStatus messageStatus,
            @Param("error") String errorText,
            @Param("date") Timestamp updateStatusDate,
            @Param("id") String uniqueIdentifier
    );

    @Modifying
    @Query("update MessageEntity m set m.status = :status, m.messageText = :text, m.dateStatus = :date " +
            "where m.uniqueMessageIdentifier = :id")
    void updateMessageOnSuccess(
            @Param("status") MessageStatus messageStatus,
            @Param("text") String mailText,
            @Param("date") Timestamp updateStatusDate,
            @Param("id") String uniqueIdentifier
    );

    @Query(value = "SELECT g.ugroup FROM group_user g", nativeQuery = true)
    List<Integer> findAllGroupUser();

    @Query(value = "SELECT t.type FROM file_type t", nativeQuery = true)
    List<String> findAllFileTypes();

    @Query(value = "SELECT t.template FROM mail_template t WHERE t.id = :id", nativeQuery = true)
    Optional<String> findMailTemplateById(@Param("id") Long id);
}
