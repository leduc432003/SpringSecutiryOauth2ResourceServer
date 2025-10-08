package org.duc.springsecurityoauth2resourceserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.duc.springsecurityoauth2resourceserver.dto.JwtInfo;
import org.duc.springsecurityoauth2resourceserver.dto.TokenPayload;
import org.duc.springsecurityoauth2resourceserver.dto.request.LoginRequest;
import org.duc.springsecurityoauth2resourceserver.dto.response.LoginResponse;
import org.duc.springsecurityoauth2resourceserver.model.RedisToken;
import org.duc.springsecurityoauth2resourceserver.model.User;
import org.duc.springsecurityoauth2resourceserver.repository.RedisTokenRepository;
import org.duc.springsecurityoauth2resourceserver.service.AuthenticationService;
import org.duc.springsecurityoauth2resourceserver.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RedisTokenRepository redisTokenRepository;

    @Override
    public LoginResponse login(final LoginRequest request) {
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        final Authentication authentication = authenticationManager.authenticate(authenticationToken);

        final User user = (User) authentication.getPrincipal();

        final TokenPayload accessToken = jwtService.generateAccessToken(user);
        final TokenPayload refreshToken = jwtService.generateRefreshToken(user);

        redisTokenRepository.save(RedisToken.builder()
                        .jwtId(refreshToken.getJwtId())
                        .expiredTime(refreshToken.getExpiredTime().getTime() - new Date().getTime())
                .build());

        return LoginResponse.builder()
                .accessToken(accessToken.getToken())
                .refreshToken(refreshToken.getToken())
                .build();
    }

    @Override
    public void logout(final String token) throws ParseException {
        final JwtInfo jwtInfo = jwtService.parseToken(token);
        final String jwtId = jwtInfo.getJwtId();
        final Date issueTime = jwtInfo.getIssueTime();
        final Date expirationTime = jwtInfo.getExpirationTime();

        if(expirationTime.before(new Date())) {
            return;
        }

        final RedisToken redisToken = RedisToken.builder()
                .jwtId(jwtId)
                .expiredTime(expirationTime.getTime() - new Date().getTime())
                .build();

        redisTokenRepository.save(redisToken);
    }
}
