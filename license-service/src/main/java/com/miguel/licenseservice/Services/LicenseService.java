package com.miguel.licenseservice.Services;

import com.miguel.licenseservice.Model.License;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LicenseService {

    Mono<License> save(License license);

    Mono<License> findById(String id);

    Flux<License> findAll();
}
