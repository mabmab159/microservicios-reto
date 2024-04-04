package com.miguel.licenseservice.Controller;

import com.miguel.licenseservice.Model.License;
import com.miguel.licenseservice.Services.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping
    public Mono<ResponseEntity<Flux<License>>> findAll() {
        return Mono.just(ResponseEntity.ok(licenseService.findAll()));
    }

    @PostMapping
    public Mono<ResponseEntity<License>> save(@RequestBody License license) {
        return licenseService.save(license).map(ResponseEntity::ok);
    }
}
