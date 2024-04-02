package com.miguel.licenseservice.Services;

import com.miguel.licenseservice.Model.License;

import java.util.List;

public interface LicenseService {

    License save(License license);
    List<License> findAll();
}
