package com.Messaging_System.application.service;

import com.Messaging_System.application.dto.output.userDataDTO.FormattedUserDTO;
import com.Messaging_System.application.dto.output.userDataDTO.SingleMessageDTO;
import com.Messaging_System.application.dto.output.userDataDTO.UserChatMessagesDTO;
import com.Messaging_System.application.dto.output.userDataDTO.UserDataDTO;
import com.Messaging_System.application.sharedServices.UserContextService;
import com.Messaging_System.domain.model.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {

    private final UserContextService contextService;
    private final UserService userService;
    private final UserFriendsService friendsService;

    public UserDataDTO getOverralStarterUserData(HttpServletRequest request, String username){

        UserModel user = (username == null)
                ? contextService.findUserByServletRequest(request)
                : userService.findUserByFullUsername(username);

        FormattedUserDTO formattedUser = userService.formatUserAsDTO(user);

        List<FormattedUserDTO> userFriends = userService.formatUserAsDTO(friendsService.getUserFriends(user));

        return new UserDataDTO(
                formattedUser, userFriends
        );

    }

    public List<SingleMessageDTO> getChatMessages(
            HttpServletRequest request,
            String username,
            int last_index
    ){
        UserModel user = contextService.findUserByServletRequest(request);
        UserModel requestedUser = userService.findUserByFullUsername(username);

        

        return null;

    }

}
