package com.miguel.cloudgateway.Feign;

import com.miguel.cloudgateway.Model.License;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "license-service")
public interface LicenseFeign {
    @PostMapping("/license")
    License save(@RequestBody License license);

    @PostMapping("/license/update")
    License updateLicense(@RequestBody License license);
}


