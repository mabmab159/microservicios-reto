package com.miguel.userservice.Services;

import com.miguel.userservice.Model.User;
import com.miguel.userservice.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<User> create(User user) {
        return userRepository.save(user);
    }
}
