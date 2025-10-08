package org.duc.springsecurityoauth2resourceserver.controller;

import lombok.RequiredArgsConstructor;
import org.duc.springsecurityoauth2resourceserver.dto.request.LoginRequest;
import org.duc.springsecurityoauth2resourceserver.dto.response.LoginResponse;
import org.duc.springsecurityoauth2resourceserver.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest loginRequest) {
        return new ResponseEntity<>(authenticationService.login(loginRequest), HttpStatus.OK);
    }
}
