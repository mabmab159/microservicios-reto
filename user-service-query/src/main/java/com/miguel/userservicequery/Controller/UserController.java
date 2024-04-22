package com.miguel.userservicequery.Controller;

import com.miguel.userservicequery.Model.AuthenticationRequest;
import com.miguel.userservicequery.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;

    @PostMapping("/auth")
    public Mono<ResponseEntity<?>> login(@RequestBody AuthenticationRequest authRequest) {
        return Mono.just(ResponseEntity.ok().body(authenticationService.authenticate(authRequest)));
    }
}
