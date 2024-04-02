package com.miguel.licenseservice.Services;

import com.miguel.licenseservice.Model.License;
import com.miguel.licenseservice.Repositories.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {
    private final LicenseRepository licenseRepository;

    @Override
    public License save(License license) {
        return licenseRepository.save(license);
    }

    @Override
    public List<License> findAll() {
        return licenseRepository.findAll();
    }
}
