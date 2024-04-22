package com.miguel.userservice.Services;

import com.miguel.userservice.Feign.UserQueryFeign;
import com.miguel.userservice.Model.ValidateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFeignService {
    private final UserQueryFeign userQueryFeign;

    public ValidateResponse validateToken(String token) {
        return userQueryFeign.validateToken(token);
    }
}
