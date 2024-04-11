package com.miguel.cloudgateway.Feign;

import com.miguel.cloudgateway.Model.AuthRequest;
import com.miguel.cloudgateway.Model.ValidateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service-query")
public interface UserQueryFeign {
    @PostMapping("/user/validate")
    ValidateResponse validateToken(@RequestHeader(value = "Authorization") String token);

    @PostMapping("/user/auth")
    Object login(@RequestBody AuthRequest authRequest);
}
