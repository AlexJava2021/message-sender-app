package org.message.sender.answering.controller.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.message.sender.answering.common.exception.MessageNotFoundException;
import org.message.sender.answering.common.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.Timestamp;
import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class RestApiExceptionHandler {
    @ExceptionHandler({MessageNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundException(
            MessageNotFoundException exception,
            HttpServletRequest request
    )
    {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request.getRequestURI(),
                Timestamp.from(Instant.now()
                )
        );
    }

    @ExceptionHandler({ValidationException.class, HttpClientErrorException.BadRequest.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse BadRequestException(Exception exception, HttpServletRequest request) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                request.getRequestURI(),
                Timestamp.from(Instant.now()
                )
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse internalException(Exception exception, HttpServletRequest request) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                request.getRequestURI(),
                Timestamp.from(Instant.now()
                )
        );
    }

}
