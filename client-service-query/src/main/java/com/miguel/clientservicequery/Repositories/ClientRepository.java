package com.miguel.clientservicequery.Repositories;

import com.miguel.clientservicequery.Model.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {
}
