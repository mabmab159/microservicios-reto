package com.miguel.licenseservice;

import com.miguel.licenseservice.Model.License;
import com.miguel.licenseservice.Services.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping
    public ResponseEntity<List<License>> findAll() {
        return ResponseEntity.ok(licenseService.findAll());
    }

    @PostMapping
    public ResponseEntity<License> save(@RequestBody License license) {
        return ResponseEntity.ok(licenseService.save(license));
    }
}
