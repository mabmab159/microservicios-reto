package com.miguel.licenseservice.Services;

import com.miguel.licenseservice.Model.License;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface LicenseService {

    Mono<License> save(License license);
    Flux<License> findAll();
}
