package com.Messaging_System.adapter.input.webcontrollers;

import com.Messaging_System.application.dto.input.UserLoginDTO;
import com.Messaging_System.application.dto.input.UserRegisterDTO;
import com.Messaging_System.application.service.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegisterDTO data){
        return ResponseEntity.ok(userService.registerNewUser(data));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAsUser(@RequestBody @Valid UserLoginDTO data){
        return ResponseEntity.ok(userService.loginAsUser(data));
    }

    @GetMapping("/tag-recommendation")
    public ResponseEntity<?> getUsableTag(
            @PathParam(value = "name") String name
    ){
        return ResponseEntity.ok(userService.getRecommendedUserTag(name));
    }

}
