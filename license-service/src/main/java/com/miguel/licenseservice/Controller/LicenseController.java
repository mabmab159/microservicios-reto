package com.miguel.licenseservice.Controller;

import com.miguel.licenseservice.Model.License;
import com.miguel.licenseservice.Services.LicenseService;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.SQLOutput;
import java.util.Date;

@RestController
@RequestMapping("/license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping
    public Mono<ResponseEntity<Flux<License>>> findAll() {
        return Mono.just(ResponseEntity.ok(licenseService.findAll()));
    }

    @GetMapping("/filter")
    public Mono<ResponseEntity<Flux<License>>> findFilter(@RequestParam String tipo, @RequestParam String id, @RequestParam String validez) {
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

    @GetMapping("/validate")
    public Mono<ResponseEntity<Mono<License>>> findById(@RequestBody String id) {
        return Mono.just(ResponseEntity.ok(licenseService.findById(id)));
    }

    @PostMapping
    public Mono<ResponseEntity<License>> save(@RequestBody License license) {
        return licenseService.save(license).map(ResponseEntity::ok);
    }

    @Scheduled(fixedRate = 300000)
    @GetMapping("/test")
    public void updateLicenses() {
        System.out.println("Scheduled");
        System.out.println(new Date());
        Flux<License> licenses = licenseService.findAll();
        //licenses = licenses.filter(p -> p.getFecha_caducidad().before(new Date()));
        licenses.map(p ->
                {
                    System.out.println(p.getFecha_caducidad());
                   // p.setActivo(false);
                   // return licenseService.save(p);
                    return null;
                }
        );
    }
}
