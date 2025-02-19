package com.Messaging_System.adapter.input.webcontrollers;

import com.Messaging_System.application.dto.input.UserLoginDTO;
import com.Messaging_System.application.dto.input.UserRegisterDTO;
import com.Messaging_System.application.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody UserRegisterDTO data
    ){
        return ResponseEntity.ok(userService.registerNewUser(data));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAsUser(
            @Valid @RequestBody UserLoginDTO data
            ){
        return ResponseEntity.ok(userService.loginAsUser(data));
    }

}
