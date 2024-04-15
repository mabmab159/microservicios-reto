package com.miguel.clientservice.Service;

import com.miguel.clientservice.Model.Client;
import com.miguel.clientservice.Repositories.ClientRepository;
import com.miguel.clientservice.Utils.KafkaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final KafkaUtil kafkaUtil;

    @Override
    public Mono<Client> save(Client client) {
        return clientRepository.save(client).map(p -> {
            kafkaUtil.sendMessage(p);
            return p;
        });
    }

    @Override
    public Mono<Client> findById(String id) {
        return clientRepository.findById(id).map(p -> {
            kafkaUtil.sendMessage(p);
            return p;
        });
    }
}
