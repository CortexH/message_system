package com.Messaging_System.adapter.exception;

import com.Messaging_System.application.dto.output.DTO_ExGeneric;
import com.Messaging_System.application.dto.output.DTO_ExUnauthorized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(CustomUnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(CustomUnauthorizedException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new DTO_ExUnauthorized(
                        LocalDateTime.now(),
                        HttpStatus.UNAUTHORIZED.value(),
                        HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                        ex.getMessage()
                )
        );
    }

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(CustomBadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new DTO_ExGeneric(LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        ex.getMessage())
        );
    }

    @ExceptionHandler(CustomConflictException.class)
    public ResponseEntity<?> handleConflictException(CustomConflictException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new DTO_ExGeneric(LocalDateTime.now(),
                        HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT.getReasonPhrase(),
                        ex.getMessage())
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElement(NoSuchElementException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new DTO_ExGeneric(LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        ex.getMessage())
        );
    }

}
