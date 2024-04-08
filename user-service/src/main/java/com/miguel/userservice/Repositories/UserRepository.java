package com.miguel.userservice.Repositories;

import com.miguel.userservice.Model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository  extends ReactiveMongoRepository<User, String> {
}
