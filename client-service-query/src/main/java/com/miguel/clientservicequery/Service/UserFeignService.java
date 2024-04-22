package com.miguel.clientservicequery.Service;


import com.miguel.clientservicequery.Feign.UserQueryFeign;
import com.miguel.clientservicequery.Model.ValidateResponse;
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
