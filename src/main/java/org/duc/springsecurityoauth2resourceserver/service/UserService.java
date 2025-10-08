package org.duc.springsecurityoauth2resourceserver.service;

import org.duc.springsecurityoauth2resourceserver.dto.request.UserCreateRequest;
import org.duc.springsecurityoauth2resourceserver.dto.response.UserCreateResponse;

public interface UserService {
    UserCreateResponse createUser(UserCreateRequest userCreateRequest);
}
