package com.miguel.licenseservicequery.Services;

import com.miguel.licenseservicequery.Feign.UserQueryFeign;
import com.miguel.licenseservicequery.Model.ValidateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserFeignService {
    private final UserQueryFeign userQueryFeign;

    public ValidateResponse validateToken(String token) {
        return userQueryFeign.validateToken(token);
    }
}
