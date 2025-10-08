package org.duc.springsecurityoauth2resourceserver.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class JwtInfo {
    private String jwtId;
    private Date issueTime;
    private Date expirationTime;
}
