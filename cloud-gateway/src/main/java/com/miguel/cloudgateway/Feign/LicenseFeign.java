package com.miguel.cloudgateway.Feign;


import com.miguel.cloudgateway.Model.License;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "license-service")
public interface LicenseFeign {
    @PostMapping("/license")
    License save(License license);

    @GetMapping("/license")
    List<License> getAll();
}
