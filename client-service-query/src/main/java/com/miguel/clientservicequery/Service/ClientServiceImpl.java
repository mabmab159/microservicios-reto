package com.miguel.clientservicequery.Service;

import com.miguel.clientservicequery.Model.Client;
import com.miguel.clientservicequery.Repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Mono<Client> findById(String id) {
        return clientRepository.findById(id);
    }
}
