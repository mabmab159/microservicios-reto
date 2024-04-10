package com.miguel.licenseservice.Controller;

import com.miguel.licenseservice.Model.License;
import com.miguel.licenseservice.Services.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

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

    @PostMapping
    public Mono<ResponseEntity<License>> save(@RequestBody License license) {
        return licenseService.save(license).map(ResponseEntity::ok);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateLicenses() {
        Flux<License> licenses = licenseService.findAll();
        licenses.filter(expiredAndErroneousLicenses)
                .map(license -> {
                    license.setActivo(false);
                    return license;
                })
                .flatMap(licenseService::save)
                .subscribe();
    }

    @PostMapping("/update")
    public Mono<ResponseEntity<License>> updateLicense(@RequestBody License license) {
        return licenseService.findById(license.getId())
                .flatMap(p -> {
                    License licenseUpdate = License.builder()
                            .id(license.getId())
                            .DNI(license.getDNI() == null ? p.getDNI() : license.getDNI())
                            .nombres(license.getNombres() == null ? p.getNombres() : license.getNombres())
                            .apellidos(license.getApellidos() == null ? p.getApellidos() : license.getApellidos())
                            .categoria(license.getCategoria() == null ? p.getCategoria() : license.getCategoria())
                            .fecha_emision(license.getFecha_emision() == null ? p.getFecha_emision() : license.getFecha_emision())
                            .fecha_caducidad(license.getFecha_caducidad() == null ? p.getFecha_caducidad() : license.getFecha_caducidad())
                            .activo(license.getActivo() == null ? p.getActivo() : license.getActivo())
                            .build();
                    return licenseService.save(licenseUpdate).map(ResponseEntity::ok);
                })
                .switchIfEmpty(licenseService.save(license).map(ResponseEntity::ok));
    }

    //Metodos de soporte
    Predicate<License> expiredAndErroneousLicenses = p -> p.getFecha_caducidad().before(new Date()) ||
            TimeUnit.MILLISECONDS.toDays(p.getFecha_caducidad().getTime() - new Date().getTime()) > 30;
}
