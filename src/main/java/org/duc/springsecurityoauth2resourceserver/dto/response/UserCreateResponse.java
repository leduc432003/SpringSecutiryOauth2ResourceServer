package org.duc.springsecurityoauth2resourceserver.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateResponse {
    private String email;
}
