package com.miguel.licenseservicequery.Services;

import com.miguel.licenseservicequery.Feign.UserQueryFeign;
import com.miguel.licenseservicequery.Model.ValidateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class UserFeignService {
    public UserQueryFeign userQueryFeign;

    public UserFeignService(UserQueryFeign userQueryFeign) {
        this.userQueryFeign = userQueryFeign;
    }

    public ValidateResponse validateToken(String token) {
        return userQueryFeign.validateToken(token);
    }
}
