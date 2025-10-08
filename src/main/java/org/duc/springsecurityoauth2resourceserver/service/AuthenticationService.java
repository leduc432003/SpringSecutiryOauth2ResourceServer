package org.duc.springsecurityoauth2resourceserver.service;

import org.duc.springsecurityoauth2resourceserver.dto.request.LoginRequest;
import org.duc.springsecurityoauth2resourceserver.dto.response.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(LoginRequest request);
}
