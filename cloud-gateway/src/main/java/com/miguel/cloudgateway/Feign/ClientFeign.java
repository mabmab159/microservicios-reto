package com.miguel.cloudgateway.Feign;

import com.miguel.cloudgateway.Model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="client-service")
public interface ClientFeign {
    @PostMapping("/client")
    Client save(@RequestBody Client client);
    @PostMapping("/client/update")
    Client update(@RequestBody Client client);
}
