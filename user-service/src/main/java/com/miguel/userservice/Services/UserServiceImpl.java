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
    public Mono<com.miguel.userservice.Security.User> findByUsername(String username) {
        Mono<User> user = userRepository.findOneByUsername(username);
        return user.flatMap(u ->
                Mono.just(
                        new com.miguel.userservice.Security.User(
                                u.getUsername(),
                                u.getPassword(),
                                true,
                                u.getRoles()
                        )
                )
        );
    }

    @Override
    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<String> login() {
        return null;
    }

    @Override
    public Mono<Boolean> validate() {
        return null;
    }
}
