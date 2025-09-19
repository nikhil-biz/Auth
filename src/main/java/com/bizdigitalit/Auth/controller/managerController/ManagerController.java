package com.bizdigitalit.Auth.controller.managerController;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @GetMapping
    @PreAuthorize("hasAuthority('CLIENT_MANAGER')")
    public ResponseEntity<String> someResources(){
        return ResponseEntity.ok().body("Only accessible to manager");
    }
}
