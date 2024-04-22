package com.miguel.userservice.Services;

import com.miguel.userservice.Model.User;
import com.miguel.userservice.Repositories.UserRepository;
import com.miguel.userservice.Utils.KafkaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final KafkaUtil kafkaUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<User> save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).map(p -> {
            kafkaUtil.sendMessage(p);
            return p;
        });
    }
}
