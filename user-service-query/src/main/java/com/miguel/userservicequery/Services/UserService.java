package com.miguel.userservicequery.Services;

import reactor.core.publisher.Mono;

public interface UserService {
    Mono<com.miguel.userservicequery.Security.User> findByUsername(String username);
}
