package com.Messaging_System.application.dto.output.userDataDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FriendMessageSentDTO {

    @JsonProperty("content")
    private String content;

    @JsonProperty("friendId")
    private String friendId;

}
