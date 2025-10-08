package org.duc.springsecurityoauth2resourceserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.duc.springsecurityoauth2resourceserver.dto.request.UserCreateRequest;
import org.duc.springsecurityoauth2resourceserver.dto.response.UserCreateResponse;
import org.duc.springsecurityoauth2resourceserver.model.User;
import org.duc.springsecurityoauth2resourceserver.repository.UserRepository;
import org.duc.springsecurityoauth2resourceserver.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserCreateResponse createUser(final UserCreateRequest userCreateRequest) {
        if(userRepository.existsByEmail(userCreateRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = User.builder()
                .email(userCreateRequest.getEmail())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()))
                .build();
        userRepository.save(user);
        return UserCreateResponse.builder()
                .email(user.getEmail())
                .build();
    }
}
