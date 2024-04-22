package com.miguel.userservicequery.Repositories;

import com.miguel.userservicequery.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
