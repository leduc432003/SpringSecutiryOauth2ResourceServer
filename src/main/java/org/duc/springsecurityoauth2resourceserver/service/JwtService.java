package org.duc.springsecurityoauth2resourceserver.service;

import com.nimbusds.jose.JOSEException;
import org.duc.springsecurityoauth2resourceserver.dto.JwtInfo;
import org.duc.springsecurityoauth2resourceserver.dto.TokenPayload;
import org.duc.springsecurityoauth2resourceserver.model.User;

import java.text.ParseException;

public interface JwtService {
    TokenPayload generateAccessToken(User user);
    TokenPayload generateRefreshToken(User user);
    boolean verifyToken(String token) throws ParseException, JOSEException;
    JwtInfo parseToken(String token) throws ParseException;
}
