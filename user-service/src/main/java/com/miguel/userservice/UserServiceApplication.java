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
    public void run(String... args) {
        User user1 = new User(null, "admin", "admin", new String[]{"GET", "POST", "PUT", "PATCH"});
        User user2 = new User(null, "get", "get", new String[]{"GET"});
        User user3 = new User(null, "post", "post", new String[]{"POST"});
        User user4 = new User(null, "edit", "edit", new String[]{"PUT", "PATCH"});
        userService.save(user1).subscribe();
        userService.save(user2).subscribe();
        userService.save(user3).subscribe();
        userService.save(user4).subscribe();
    }
}
