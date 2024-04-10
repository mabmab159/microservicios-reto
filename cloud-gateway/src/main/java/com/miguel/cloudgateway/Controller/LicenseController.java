package com.miguel.cloudgateway.Controller;

import com.miguel.cloudgateway.Model.License;
import com.miguel.cloudgateway.Service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/license")
@RequiredArgsConstructor
public class LicenseController {
    private final LicenseService licenseService;

    @PostMapping
    public ResponseEntity<License> saveLicense(@RequestBody License license) {
        return ResponseEntity.ok(licenseService.save(license));
    }

    @GetMapping
    public ResponseEntity<List<License>> getAll() {
        return ResponseEntity.ok(licenseService.getAll());
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<License>> findFilter(@RequestParam String tipo, @RequestParam String id, @RequestParam String validez) {
        return ResponseEntity.ok(licenseService.findFilter(tipo, id, validez));
    }

    @GetMapping(value = "/validate/{id}")
    public ResponseEntity<License> findFilter(@PathVariable String id) {
        return ResponseEntity.ok(licenseService.findById(id));
    }

    @PatchMapping
    public ResponseEntity<License> updateLicense(@RequestBody License license) {
        return ResponseEntity.ok(licenseService.updateLicense(license));
    }
}


