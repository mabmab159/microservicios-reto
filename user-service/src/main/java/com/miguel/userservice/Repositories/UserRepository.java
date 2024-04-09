package com.miguel.userservice.Repositories;

import com.miguel.userservice.Model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findOneByUsername(String username);
}
