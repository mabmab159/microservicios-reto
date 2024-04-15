package com.miguel.clientservice.Service;

import com.miguel.clientservice.Model.Client;
import reactor.core.publisher.Mono;

public interface ClientService {
    Mono<Client> save(Client client);
    Mono<Client> findById(String id);
}
