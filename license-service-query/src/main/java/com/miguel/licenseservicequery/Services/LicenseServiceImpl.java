package com.miguel.licenseservicequery.Services;

import com.miguel.licenseservicequery.Model.Audit;
import com.miguel.licenseservicequery.Model.License;
import com.miguel.licenseservicequery.Repositories.LicenseRepository;
import com.miguel.licenseservicequery.Utils.KafkaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {
    private final LicenseRepository licenseRepository;
    private final KafkaUtil kafkaUtil;

    @Override
    public Mono<License> findById(String id) {
        kafkaUtil.sendMessage(new Audit(null, "License | findById: " + id, new Date()));
        return licenseRepository.findById(id);
    }

    @Override
    public Flux<License> findAll() {
        kafkaUtil.sendMessage(new Audit(null, "License | findAll", new Date()));
        return licenseRepository.findAll();
    }
}
