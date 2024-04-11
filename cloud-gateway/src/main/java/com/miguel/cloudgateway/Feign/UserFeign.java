package com.miguel.cloudgateway.Feign;

import com.miguel.cloudgateway.Model.AuthRequest;
import com.miguel.cloudgateway.Model.User;
import com.miguel.cloudgateway.Model.ValidateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service")
public interface UserFeign {
    @PostMapping("/user/validate")
    ValidateResponse validateToken(@RequestHeader(value = "Authorization") String token);

    @PostMapping("/user/create")
    User createUser(@RequestBody User user);

    @PostMapping("/user/auth")
    Object login(@RequestBody AuthRequest authRequest);

}
