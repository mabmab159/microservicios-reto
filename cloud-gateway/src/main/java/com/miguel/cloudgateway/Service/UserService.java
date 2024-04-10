package com.miguel.cloudgateway.Service;

import com.miguel.cloudgateway.Feign.UserFeign;
import com.miguel.cloudgateway.Model.AuthRequest;
import com.miguel.cloudgateway.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserFeign userFeign;

    public Boolean validateToken(String token) {
        return userFeign.validateToken(token);
    }

    public User createUser(User user) {
        return userFeign.createUser(user);
    }

    public Object login(AuthRequest authRequest) {
        return userFeign.login(authRequest);
    }
}
