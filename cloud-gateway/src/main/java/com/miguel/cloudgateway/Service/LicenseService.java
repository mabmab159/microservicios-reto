package com.miguel.cloudgateway.Service;

import com.miguel.cloudgateway.Feign.LicenseFeign;
import com.miguel.cloudgateway.Model.License;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenseService {
    private final LicenseFeign licenseFeign;

    public License save(License license) {
        return licenseFeign.save(license);
    }

    public List<License> getAll() {
        return licenseFeign.getAll();
    }
}
