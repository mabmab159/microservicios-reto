package com.miguel.licenseservice.Controller;

import com.miguel.licenseservice.Model.Client;
import com.miguel.licenseservice.Model.License;
import com.miguel.licenseservice.Services.ClientService;
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
    private final ClientService clientService;

    @PostMapping
    public Mono<ResponseEntity<License>> save(@RequestBody License license) {
        Client client = clientService.getClient(license.getClient().getDNI());
        license.setClient(client);
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

    @PatchMapping("/update")
    public Mono<ResponseEntity<License>> updateLicense(@RequestBody License license) {
        Client client = clientService.getClient(license.getClient().getDNI());
        if (client == null) {
            return Mono.just(ResponseEntity.notFound().build());
        }
        return licenseService.findById(license.getId())
                .flatMap(p -> {
                    License licenseUpdate = License.builder()
                            .id(license.getId())
                            .client(client)
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
