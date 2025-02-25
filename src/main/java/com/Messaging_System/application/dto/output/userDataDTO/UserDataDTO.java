package com.Messaging_System.application.dto.output.userDataDTO;

import java.util.List;

public record UserDataDTO(
        FormattedUserDTO user,
        List<FormattedUserDTO> Friends
) { }
