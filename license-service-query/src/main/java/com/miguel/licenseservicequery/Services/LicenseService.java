package com.miguel.licenseservicequery.Services;

import com.miguel.licenseservicequery.Model.License;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LicenseService {
    Mono<License> findById(String id);

    Flux<License> findAll();
}
