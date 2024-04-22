package com.miguel.userservice.Controller;

import com.miguel.userservice.Model.User;
import com.miguel.userservice.Model.ValidateResponse;
import com.miguel.userservice.Services.UserFeignService;
import com.miguel.userservice.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserFeignService userFeignService;

    @PostMapping("/create")
    public Mono<ResponseEntity<User>> create(@RequestBody User user, @RequestHeader(value = "Authorization") String token) {
        ValidateResponse validateResponse = userFeignService.validateToken(token);
        List<String> roles = validateResponse.getRoles();
        if (roles.contains("POST") && validateResponse.getSuccess()) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            return userService.save(user).map(ResponseEntity::ok);
        }
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
