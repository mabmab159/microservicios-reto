package com.miguel.cloudgateway.Service;

import com.miguel.cloudgateway.Feign.LicenseFeign;
import com.miguel.cloudgateway.Model.License;
import com.miguel.cloudgateway.Utils.Filtros;
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

    public List<License> findFilter(String tipo, String id, String validez) {
        return licenseFeign.findFilter(
                Filtros.builder()
                        .tipo(tipo)
                        .id(id)
                        .validez(validez)
                        .build());
    }

    public License findById(String id) {
        return licenseFeign.findById(id);
    }

    public License updateLicense(License license){
        return licenseFeign.updateLicense(license);
    }
}
