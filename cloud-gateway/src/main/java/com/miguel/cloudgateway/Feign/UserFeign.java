package com.miguel.cloudgateway.Feign;

import com.miguel.cloudgateway.Model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserFeign {
    @PostMapping("/user/create")
    User createUser(@RequestBody User user);
}
