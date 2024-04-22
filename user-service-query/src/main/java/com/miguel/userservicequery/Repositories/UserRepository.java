package com.miguel.userservicequery.Repositories;

import com.miguel.userservicequery.Model.User;
import com.miguel.userservicequery.Model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    UserEntity findByUsername(String username);
}
