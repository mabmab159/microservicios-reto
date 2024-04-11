package com.miguel.userservicequery.Controller;

import com.miguel.userservicequery.Model.User;
import com.miguel.userservicequery.Model.ValidateResponse;
import com.miguel.userservicequery.Security.AuthRequest;
import com.miguel.userservicequery.Security.AuthResponse;
import com.miguel.userservicequery.Security.ErrorLogin;
import com.miguel.userservicequery.Security.JwtUtil;
import com.miguel.userservicequery.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/auth")
    public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest authRequest) {
        return userService.findByUsername(authRequest.getUsername())
                .map(user -> {
                    if (BCrypt.checkpw(authRequest.getPassword(), user.getPassword())) {
                        String token = jwtUtil.generateToken(user);
                        Date expiration = jwtUtil.getExpirationDateFromToken(token);
                        return ResponseEntity.ok(new AuthResponse(token, expiration));
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(new ErrorLogin("Bad Credentials", new Date()));
                    }
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/validate")
    public Mono<ResponseEntity<ValidateResponse>> validate(@RequestHeader(value = "Authorization") String token) {
        token = token.substring(6);
        return Mono.just(
                ValidateResponse.builder()
                        .success(jwtUtil.validateToken(token))
                        .roles(Arrays.stream(jwtUtil.getAllClaimsFromToken(token)
                                .get("roles")
                                .toString()
                                .replaceAll("[\\[\\]\"]", "")
                                .split(", ")
                        ).toList())
                        .build()
        ).map(ResponseEntity::ok);
    }
}
