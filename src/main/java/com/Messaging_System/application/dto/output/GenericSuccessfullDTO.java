package com.Messaging_System.application.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record GenericSuccessfullDTO(
        @JsonProperty("timestamp")
        String timestamp,
        @JsonProperty("code")
        Integer code,
        @JsonProperty("message")
        String message
) {
}
