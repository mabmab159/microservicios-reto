package com.miguel.cloudgateway.Service;

import com.miguel.cloudgateway.Feign.ClientFeign;
import com.miguel.cloudgateway.Feign.ClientQueryFeign;
import com.miguel.cloudgateway.Model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientFeign clientFeign;
    private final ClientQueryFeign clientQueryFeign;

    public Client saveClient(Client client) {
        return clientFeign.save(client);
    }

    public Client updateClient(Client client) {
        return clientFeign.update(client);
    }

    public Client getClient(String id) {
        return clientQueryFeign.getClient(id);
    }
}
