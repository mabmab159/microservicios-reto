package com.miguel.clientservice.Controller;

import com.miguel.clientservice.Model.Client;
import com.miguel.clientservice.Service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public Mono<ResponseEntity<Client>> saveClient(@RequestBody Client client) {
        return clientService.save(client).map(ResponseEntity::ok);
    }

    @PatchMapping("/update")
    public Mono<ResponseEntity<Client>> updateClient(@RequestBody Client client) {
        return clientService.findById(client.getId())
                .flatMap(p -> {
                    Client clientUpdate = Client.builder()
                            .id(client.getId())
                            .dni(client.getDni() == null ? p.getDni() : client.getDni())
                            .nombres(client.getNombres() == null ? p.getNombres() : client.getNombres())
                            .apellidos(client.getApellidos() == null ? p.getApellidos() : client.getApellidos())
                            .fechaNacimiento(client.getFechaNacimiento() == null ? p.getFechaNacimiento() : client.getFechaNacimiento())
                            .build();
                    return clientService.save(clientUpdate).map(ResponseEntity::ok);
                })
                .switchIfEmpty(clientService.save(client).map(ResponseEntity::ok));
    }
}
