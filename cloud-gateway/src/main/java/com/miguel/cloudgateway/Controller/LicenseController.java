package com.miguel.cloudgateway.Controller;

import com.miguel.cloudgateway.Model.License;
import com.miguel.cloudgateway.Service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/license")
@RequiredArgsConstructor
public class LicenseController {
    private final LicenseService licenseService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<License> saveLicense(@RequestBody License license) {
        return ResponseEntity.ok(licenseService.save(license));
    }
}


