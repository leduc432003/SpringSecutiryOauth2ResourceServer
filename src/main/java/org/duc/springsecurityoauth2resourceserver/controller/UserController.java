package org.duc.springsecurityoauth2resourceserver.controller;

import lombok.RequiredArgsConstructor;
import org.duc.springsecurityoauth2resourceserver.dto.request.UserCreateRequest;
import org.duc.springsecurityoauth2resourceserver.dto.response.UserCreateResponse;
import org.duc.springsecurityoauth2resourceserver.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserCreateResponse> createResponse(@RequestBody final UserCreateRequest request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }
}
