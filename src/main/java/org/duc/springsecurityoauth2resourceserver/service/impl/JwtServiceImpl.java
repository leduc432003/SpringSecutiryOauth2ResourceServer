package org.duc.springsecurityoauth2resourceserver.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.duc.springsecurityoauth2resourceserver.dto.JwtInfo;
import org.duc.springsecurityoauth2resourceserver.dto.TokenPayload;
import org.duc.springsecurityoauth2resourceserver.model.RedisToken;
import org.duc.springsecurityoauth2resourceserver.model.User;
import org.duc.springsecurityoauth2resourceserver.repository.RedisTokenRepository;
import org.duc.springsecurityoauth2resourceserver.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiredTime}")
    private Long expiredTime;
    @Value("${jwt.expiredTimeRefreshToken}")
    private Long expiredTimeRefreshToken;

    private final RedisTokenRepository redisTokenRepository;

    @Override
    public TokenPayload generateAccessToken(final User user) {
        final JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        final String jwtId = UUID.randomUUID().toString();
        final Date issueTime = new Date();
        final Date expirationTime = Date.from(issueTime.toInstant().plus(expiredTime, ChronoUnit.MINUTES));

        final JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .jwtID(jwtId)
                .subject(user.getEmail())
                .issueTime(issueTime)
                .expirationTime(expirationTime)
                .build();

        final Payload payload = new Payload(claimsSet.toJSONObject());

        final JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

        final String token = jwsObject.serialize();

        return TokenPayload.builder()
                .token(token)
                .jwtId(jwtId)
                .expiredTime(expirationTime)
                .build();
    }

    @Override
    public TokenPayload generateRefreshToken(final User user) {
        final JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        final Date issueTime = new Date();
        final Date expirationTime = Date.from(issueTime.toInstant().plus(expiredTimeRefreshToken, ChronoUnit.DAYS));
        final String jwtId = UUID.randomUUID().toString();

        final JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .jwtID(jwtId)
                .subject(user.getEmail())
                .issueTime(issueTime)
                .expirationTime(expirationTime)
                .build();

        final Payload payload = new Payload(claimsSet.toJSONObject());

        final JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

        final String token = jwsObject.serialize();

        return TokenPayload.builder()
                .token(token)
                .jwtId(jwtId)
                .expiredTime(expirationTime)
                .build();
    }

    @Override
    public boolean verifyToken(final String token) throws ParseException, JOSEException {
        final SignedJWT signedJWT = SignedJWT.parse(token);
        final Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        if(expirationTime.before(new Date())) {
            return false;
        };

        final String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        final Optional<RedisToken> redisToken = redisTokenRepository.findById(jwtId);
        if(redisToken.isPresent()) {
            throw new RuntimeException("Token is invalid.");
        }
        return signedJWT.verify(new MACVerifier(secretKey));
    }

    @Override
    public JwtInfo parseToken(final String token) throws ParseException {
        final SignedJWT signedJWT = SignedJWT.parse(token);
        final String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        final Date issueTime = signedJWT.getJWTClaimsSet().getIssueTime();
        final Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        return JwtInfo.builder()
                .jwtId(jwtId)
                .issueTime(issueTime)
                .expirationTime(expirationTime)
                .build();
    }
}
