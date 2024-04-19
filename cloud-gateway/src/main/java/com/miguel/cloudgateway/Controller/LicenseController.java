package com.miguel.cloudgateway.Controller;

import com.miguel.cloudgateway.Model.License;
import com.miguel.cloudgateway.Model.ValidateResponse;
import com.miguel.cloudgateway.Service.LicenseService;
import com.miguel.cloudgateway.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/license")
@RequiredArgsConstructor
public class LicenseController {
    private final LicenseService licenseService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<License> saveLicense(@RequestBody License license, @RequestHeader(value = "Authorization") String token) {
        ValidateResponse validateResponse = userService.validateToken(token);
        List<String> roles = validateResponse.getRoles();
        if (roles.contains("POST") && validateResponse.getSuccess()) {
            return ResponseEntity.ok(licenseService.save(license));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping
    public ResponseEntity<List<License>> getAll(@RequestHeader(value = "Authorization") String token) {
        ValidateResponse validateResponse = userService.validateToken(token);
        List<String> roles = validateResponse.getRoles();
        if (roles.contains("GET") && validateResponse.getSuccess()) {

            return ResponseEntity.ok(licenseService.getAll());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<License>> findFilter(@RequestParam String tipo, @RequestParam String id,
                                                    @RequestParam String validez, @RequestHeader(value = "Authorization") String token) {
        ValidateResponse validateResponse = userService.validateToken(token);
        List<String> roles = validateResponse.getRoles();
        if (roles.contains("GET") && validateResponse.getSuccess()) {
            return ResponseEntity.ok(licenseService.findFilter(tipo, id, validez));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @GetMapping(value = "/validate/{id}")
    public ResponseEntity<License> findFilter(@PathVariable String
                                                      id, @RequestHeader(value = "Authorization") String token) {
        ValidateResponse validateResponse = userService.validateToken(token);
        List<String> roles = validateResponse.getRoles();
        if (roles.contains("GET") && validateResponse.getSuccess()) {
            return ResponseEntity.ok(licenseService.findById(id));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PatchMapping
    public ResponseEntity<License> updateLicense(@RequestBody License
                                                         license, @RequestHeader(value = "Authorization") String token) {
        ValidateResponse validateResponse = userService.validateToken(token);
        List<String> roles = validateResponse.getRoles();
        if (roles.contains("PATCH") && validateResponse.getSuccess()) {
            return ResponseEntity.ok(licenseService.updateLicense(license));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}


