package com.miguel.licenseservice.Repositories;

import com.miguel.licenseservice.Model.License;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LicenseRepository extends ReactiveMongoRepository<License, String> {
}
