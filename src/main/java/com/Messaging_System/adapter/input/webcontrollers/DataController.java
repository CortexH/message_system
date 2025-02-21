package com.Messaging_System.adapter.input.webcontrollers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/data")
@RestController
@RequiredArgsConstructor
public class DataController {

    @GetMapping("/user/get-starter-data")
    public ResponseEntity<?> loadUserStarterData(
            HttpServletRequest request
    ){
        return ResponseEntity.ok("A");
    }

    @GetMapping("/message/get-from-user")
    public ResponseEntity<?> loadMessagesFromSpecifiedUser(
            HttpServletRequest request,
            @PathParam("id") UUID id,
            @PathParam("last") Integer last
            ) {
        return ResponseEntity.ok("A");
    }

}
