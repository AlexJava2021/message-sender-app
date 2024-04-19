package org.message.sender.answering.controller.exception.handler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
@Schema(description = "Error Dto contains information about error occurred in the application.")
public class ErrorResponse {
    private final HttpStatus status;
    private final String error;
    private final String path;
    private final Timestamp timestamp;
}
