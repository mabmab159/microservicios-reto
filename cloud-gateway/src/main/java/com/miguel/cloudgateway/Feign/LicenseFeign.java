package com.miguel.cloudgateway.Feign;


import com.miguel.cloudgateway.Model.License;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "license-service")
public interface LicenseFeign {
    @PostMapping(value = "/licenses", consumes = "application/json")
    License save(License license);
}
