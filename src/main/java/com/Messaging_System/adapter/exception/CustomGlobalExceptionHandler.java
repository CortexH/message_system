package com.Messaging_System.adapter.exception;

import com.Messaging_System.application.dto.output.exceptions.DTO_ExGeneric;
import com.Messaging_System.application.dto.output.exceptions.DTO_ExUnauthorized;
import com.Messaging_System.application.dto.output.exceptions.DTO_ExValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex){

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(new DTO_ExValidation(
                LocalDateTime.now(),
                400, "Null or blank fields",
                errors
        ));
    }

    @ExceptionHandler(CustomUnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(CustomUnauthorizedException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new DTO_ExUnauthorized(
                        LocalDateTime.now().toString(),
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

    @ExceptionHandler(CustomInternalException.class)
    public ResponseEntity<?> handleInternalException(CustomInternalException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new DTO_ExGeneric(LocalDateTime.now(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                        ex.getMessage())
        );
    }

}
