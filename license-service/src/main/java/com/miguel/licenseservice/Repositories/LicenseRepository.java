package com.miguel.licenseservice.Repositories;

import com.miguel.licenseservice.Model.License;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends ReactiveMongoRepository<License, String> {
}
