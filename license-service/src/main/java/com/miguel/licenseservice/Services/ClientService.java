package com.miguel.licenseservice.Services;

import com.miguel.licenseservice.Feign.ClientQueryFeign;
import com.miguel.licenseservice.Model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientQueryFeign clientQueryFeign;

    public Client getClient(String id) {
        return clientQueryFeign.getClient(id);
    }
}
