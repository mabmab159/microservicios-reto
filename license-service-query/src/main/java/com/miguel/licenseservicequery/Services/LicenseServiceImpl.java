package com.miguel.licenseservicequery.Services;

import com.miguel.licenseservicequery.Model.License;
import com.miguel.licenseservicequery.Repositories.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {
    private final LicenseRepository licenseRepository;

    @Override
    public Mono<License> findById(String id) {
        return licenseRepository.findById(id);
    }

    @Override
    public Flux<License> findAll() {
        return licenseRepository.findAll();
    }
}
