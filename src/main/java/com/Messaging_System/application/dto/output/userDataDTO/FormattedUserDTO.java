package com.Messaging_System.application.dto.output.userDataDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FormattedUserDTO {

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userImage")
    private String userImage;

    @JsonProperty("userBio")
    private String bio;
}
