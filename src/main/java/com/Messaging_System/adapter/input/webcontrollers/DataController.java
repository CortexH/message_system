package com.Messaging_System.adapter.input.webcontrollers;

import com.Messaging_System.application.service.DataService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class DataController {
    private final DataService dataService;

    // used when user open the website
    @GetMapping("/user/get-starter-data")
    public ResponseEntity<?> loadUserStarterData(
            HttpServletRequest request,
            @PathParam(value = "username") String username
    ){
        return ResponseEntity.ok(dataService.getOverralStarterUserData(request, username));
    }

    // used when user open a chat or scroll up
    @GetMapping("/message/get-from-user")
    public ResponseEntity<?> loadMessagesFromSpecifiedUser(
            HttpServletRequest request,
            @PathParam("username") String username,
            @PathParam("last_index") Integer last_index
    ) {
        return ResponseEntity.ok(dataService.getChatMessages(request, username, last_index));
    }

}


