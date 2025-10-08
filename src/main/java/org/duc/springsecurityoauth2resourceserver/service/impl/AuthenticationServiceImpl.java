package org.duc.springsecurityoauth2resourceserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.duc.springsecurityoauth2resourceserver.dto.request.LoginRequest;
import org.duc.springsecurityoauth2resourceserver.dto.response.LoginResponse;
import org.duc.springsecurityoauth2resourceserver.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(final LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return LoginResponse.builder()
                .accessToken("Access Token")
                .refreshToken("Refresh Token")
                .build();
    }
}
