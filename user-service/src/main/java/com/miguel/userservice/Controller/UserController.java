package com.miguel.userservice.Controller;

import com.miguel.userservice.Configuration.JwtService;
import com.miguel.userservice.Model.User;
import com.miguel.userservice.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class UserController {
    private final JwtService jwtService;

    private final UserService userService;

    @PostMapping("/auth")
    public Mono<ResponseEntity<String>> login(@RequestBody User user) {
        return Mono.just(jwtService.generateToken(user)).map(ResponseEntity::ok);
        //return Mono.just("login").map(ResponseEntity::ok);
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<User>> create(User user) {
        return userService.create(user).map(ResponseEntity::ok);
    }

    @PostMapping("/validate")
    public Mono<ResponseEntity<Boolean>> validate() {
        return Mono.just(true).map(ResponseEntity::ok);
    }
}
