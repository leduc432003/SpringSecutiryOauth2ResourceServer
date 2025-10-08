package org.duc.springsecurityoauth2resourceserver.service;

import org.duc.springsecurityoauth2resourceserver.dto.request.LoginRequest;
import org.duc.springsecurityoauth2resourceserver.dto.response.LoginResponse;

import java.text.ParseException;

public interface AuthenticationService {
    LoginResponse login(LoginRequest request);
    void logout(String token) throws ParseException;
}
