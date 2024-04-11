package com.miguel.userservicequery.Services;

import com.miguel.userservicequery.Model.User;
import com.miguel.userservicequery.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<com.miguel.userservicequery.Security.User> findByUsername(String username) {
        Mono<User> user = userRepository.findOneByUsername(username);
        return user.flatMap(u ->
                Mono.just(
                        new com.miguel.userservicequery.Security.User(
                                u.getUsername(),
                                u.getPassword(),
                                u.getRoles()
                        )
                )
        );
    }
}
