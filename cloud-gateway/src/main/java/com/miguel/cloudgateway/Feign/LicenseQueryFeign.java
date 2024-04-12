package com.miguel.cloudgateway.Feign;

import com.miguel.cloudgateway.Model.License;
import com.miguel.cloudgateway.Utils.Filtros;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "license-service-query")
public interface LicenseQueryFeign {
    @GetMapping("/license")
    List<License> getAll();

    @GetMapping("/license/filter")
    List<License> findFilter(@SpringQueryMap Filtros filtros);

    @GetMapping("/license/validate/{id}")
    License findById(@PathVariable("id") String id);
}
