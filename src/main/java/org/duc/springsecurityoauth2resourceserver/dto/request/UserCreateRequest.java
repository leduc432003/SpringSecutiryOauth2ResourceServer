package org.duc.springsecurityoauth2resourceserver.dto.request;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String email;
    private String password;
}
