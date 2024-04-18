package com.miguel.cloudgateway.Feign;

import com.miguel.cloudgateway.Model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-service-query")
public interface ClientQueryFeign {
    @GetMapping("/client/{id}")
    Client getClient(@RequestParam String id);
}
