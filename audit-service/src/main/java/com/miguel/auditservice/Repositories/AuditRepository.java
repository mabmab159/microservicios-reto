package com.miguel.auditservice.Repositories;

import com.miguel.auditservice.Model.Audit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends ReactiveMongoRepository<Audit, String> {
}
