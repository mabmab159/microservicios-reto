package com.miguel.licenseservicequery.Controller;

import com.miguel.licenseservicequery.Model.License;
import com.miguel.licenseservicequery.Model.ValidateResponse;
import com.miguel.licenseservicequery.Services.LicenseService;
import com.miguel.licenseservicequery.Services.UserFeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/license")
@RequiredArgsConstructor
@EnableScheduling
public class LicenseController {
    private final LicenseService licenseService;
    private final UserFeignService userFeignService;

    @GetMapping
    public Mono<ResponseEntity<Flux<License>>> findAll(@RequestHeader(value = "Authorization") String token) {
        ValidateResponse validateResponse = userFeignService.validateToken(token);
        List<String> roles = validateResponse.getRoles();
        if (roles.contains("GET") && validateResponse.getSuccess()) {
            return Mono.just(ResponseEntity.ok(licenseService.findAll()));
        }
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/filter")
    public Mono<ResponseEntity<Flux<License>>> findFilter(@RequestParam(required = false, defaultValue = "") String tipo,
                                                          @RequestParam(required = false, defaultValue = "") String id,
                                                          @RequestParam(required = false, defaultValue = "") String validez,
                                                          @RequestHeader(value = "Authorization") String token) {
        ValidateResponse validateResponse = userFeignService.validateToken(token);
        List<String> roles = validateResponse.getRoles();
        if (roles.contains("GET") && validateResponse.getSuccess()) {

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
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/validate/{id}")
    public Mono<ResponseEntity<License>> findById(@PathVariable String id, @RequestHeader(value = "Authorization") String token) {
        ValidateResponse validateResponse = userFeignService.validateToken(token);
        List<String> roles = validateResponse.getRoles();
        if (roles.contains("GET") && validateResponse.getSuccess()) {
            return licenseService.findById(id).map(ResponseEntity::ok);
        }
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}