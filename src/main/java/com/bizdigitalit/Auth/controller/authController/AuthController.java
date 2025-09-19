package com.bizdigitalit.Auth.controller.authController;

import com.bizdigitalit.Auth.dto.UserDto;
import com.bizdigitalit.Auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> singUp(@RequestBody UserDto userSingUpDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userSingUpDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody UserDto userSignInDto){
        return ResponseEntity.ok().body(userService.signInUser(userSignInDto));
    }
}
