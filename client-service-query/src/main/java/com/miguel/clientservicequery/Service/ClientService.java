package com.miguel.clientservicequery.Service;

import com.miguel.clientservicequery.Model.Client;
import reactor.core.publisher.Mono;

public interface ClientService {
    Mono<Client> findById(String id);
}
