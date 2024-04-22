package com.miguel.licenseservicequery.Controller;

import com.miguel.licenseservicequery.Model.License;
import com.miguel.licenseservicequery.Services.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/license")
@RequiredArgsConstructor
@EnableScheduling
public class LicenseController {
    private final LicenseService licenseService;

    @GetMapping
    public Mono<ResponseEntity<Flux<License>>> findAll() {
        return Mono.just(ResponseEntity.ok(licenseService.findAll()));
    }

    @GetMapping("/filter")
    public Mono<ResponseEntity<Flux<License>>> findFilter(@RequestParam(required = false, defaultValue = "") String tipo,
                                                          @RequestParam(required = false, defaultValue = "") String id,
                                                          @RequestParam(required = false, defaultValue = "") String validez) {
        Flux<License> licenses = licenseService.findAll();
        if (!tipo.isEmpty()) {
            licenses = licenses.filter(p -> p.getCategoria().name().equals(tipo));
        }
        if (!id.isEmpty()) {
            licenses = licenses.filter(p -> p.getId().equals(id));
        }
        if (!validez.isEmpty()) {
            licenses = licenses.filter(p -> validez.equals(String.valueOf(p.getActivo())));
        }
        return Mono.just(ResponseEntity.ok(licenses));
    }

    @GetMapping("/validate/{id}")
    public Mono<ResponseEntity<License>> findById(@PathVariable String id) {
        return licenseService.findById(id).map(ResponseEntity::ok);
    }
}