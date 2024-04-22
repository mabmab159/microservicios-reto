package com.miguel.clientservice.Service;

import com.miguel.clientservice.Feign.UserQueryFeign;
import com.miguel.clientservice.Model.ValidateResponse;
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
