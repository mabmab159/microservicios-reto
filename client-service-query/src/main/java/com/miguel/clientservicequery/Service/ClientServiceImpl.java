package com.miguel.clientservicequery.Service;

import com.miguel.clientservicequery.Model.Audit;
import com.miguel.clientservicequery.Model.Client;
import com.miguel.clientservicequery.Repositories.ClientRepository;
import com.miguel.clientservicequery.Utils.KafkaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final KafkaUtil kafkaUtil;

    @Override
    public Mono<Client> findById(String id) {
        kafkaUtil.sendMessage(new Audit(null, "License | findById: " + id, new Date()));
        return clientRepository.findById(id);
    }
}
