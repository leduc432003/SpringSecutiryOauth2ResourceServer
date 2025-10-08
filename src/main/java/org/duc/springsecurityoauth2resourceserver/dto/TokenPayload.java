package org.duc.springsecurityoauth2resourceserver.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TokenPayload {
    private String token;
    private String jwtId;
    private Date expiredTime;
}
