package com.bizdigitalit.Auth.controller.userController;

import com.bizdigitalit.Auth.dto.SignupRequest;
import com.bizdigitalit.Auth.exception.UserNotFoundException;
import com.bizdigitalit.Auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<SignupRequest>> getAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<SignupRequest> byEmail(@PathVariable String email) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

}
