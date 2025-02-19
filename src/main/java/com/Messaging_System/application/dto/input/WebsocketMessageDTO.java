package com.Messaging_System.application.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WebsocketMessageDTO {

    @JsonProperty("content")
    private String content;

    @JsonProperty("receiverId")
    private String receiverId;

}
