package com.miguel.licenseservicequery.Repositories;

import com.miguel.licenseservicequery.Model.License;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LicenseRepository extends ReactiveMongoRepository<License, String> {
}
