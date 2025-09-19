package com.bizdigitalit.Auth.controller.authController;

import com.bizdigitalit.Auth.dto.SignInRequest;
import com.bizdigitalit.Auth.dto.SignupRequest;
import com.bizdigitalit.Auth.service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<String> singUp(@Valid @RequestBody SignupRequest userSingUpDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userSingUpDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody SignInRequest userSignInDto){
        return ResponseEntity.ok().body(userService.signInUser(userSignInDto));
    }
}
