package com.miguel.clientservice.Service;

import com.miguel.clientservice.Model.Client;
import com.miguel.clientservice.Repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Mono<Client> save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Mono<Client> findById(String id) {
        return clientRepository.findById(id);
    }
}
