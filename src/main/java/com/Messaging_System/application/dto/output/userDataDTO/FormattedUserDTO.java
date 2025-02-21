package com.Messaging_System.application.dto.output.userDataDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FormattedUserDTO(
        @JsonProperty("userName")
        String userName,

        @JsonProperty("userId")
        String userId,

        @JsonProperty("userImage")
        String userImage,

        @JsonProperty("userBio")
        String bio
) {
}
