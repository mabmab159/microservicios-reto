package com.miguel.userservice;

import com.miguel.userservice.Model.User;
import com.miguel.userservice.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class UserServiceApplication implements CommandLineRunner {
    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String[] roles = {"asd", "asd"};
        User user = new User(null, "miguel", "miguel", new String[]{"GET", "POST", "PUT"});
        userService.save(user).subscribe();
    }
}
