package org.duc.springsecurityoauth2resourceserver.controller;

import lombok.RequiredArgsConstructor;
import org.duc.springsecurityoauth2resourceserver.dto.request.LoginRequest;
import org.duc.springsecurityoauth2resourceserver.dto.response.LoginResponse;
import org.duc.springsecurityoauth2resourceserver.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest loginRequest) {
        return new ResponseEntity<>(authenticationService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader) throws ParseException {
        String token = authHeader.replace("Bearer ", "");
        authenticationService.logout(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
