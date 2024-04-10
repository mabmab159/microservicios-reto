package com.miguel.cloudgateway.Controller;

import com.miguel.cloudgateway.Model.AuthRequest;
import com.miguel.cloudgateway.Model.ErrorLogin;
import com.miguel.cloudgateway.Model.User;
import com.miguel.cloudgateway.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<Object> login(@RequestBody AuthRequest authRequest) {
        try {
            return ResponseEntity.ok(userService.login(authRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorLogin("Bad Credentials", new Date()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(userService.validateToken(token));
    }
}
