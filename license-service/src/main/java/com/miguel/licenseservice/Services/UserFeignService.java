package com.miguel.licenseservice.Services;

import com.miguel.licenseservice.Feign.UserQueryFeign;
import com.miguel.licenseservice.Model.ValidateResponse;
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
