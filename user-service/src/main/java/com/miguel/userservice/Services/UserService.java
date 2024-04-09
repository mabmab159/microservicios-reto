package com.miguel.userservice.Services;

import com.miguel.userservice.Model.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<com.miguel.userservice.Security.User> findByUsername(String username);

    Mono<User> create(User user);

    Mono<String> login();

    Mono<Boolean> validate();
}
