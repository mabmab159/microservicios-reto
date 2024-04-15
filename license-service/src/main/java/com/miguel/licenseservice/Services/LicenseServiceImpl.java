package com.miguel.licenseservice.Services;

import com.miguel.licenseservice.Model.Audit;
import com.miguel.licenseservice.Model.License;
import com.miguel.licenseservice.Repositories.LicenseRepository;
import com.miguel.licenseservice.Utils.KafkaUtil;
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
    public Mono<License> save(License license) {
        return licenseRepository.save(license).map(p -> {
            kafkaUtil.sendMessage(p);
            kafkaUtil.sendMessageAudit(new Audit(null,"License | save: " + license, new Date()));
            return p;
        });
    }

    @Override
    public Mono<License> findById(String id) {
        kafkaUtil.sendMessageAudit(new Audit(null,"License | findById: " + id, new Date()));
        return licenseRepository.findById(id);
    }

    @Override
    public Flux<License> findAll() {
        kafkaUtil.sendMessageAudit(new Audit(null,"License | findAll", new Date()));
        return licenseRepository.findAll();
    }
}
