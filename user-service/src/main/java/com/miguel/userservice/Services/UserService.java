package com.miguel.userservice.Services;

import com.miguel.userservice.Model.User;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> create(User user);
    Mono<String> login();

    Mono<Boolean> validate();
}
