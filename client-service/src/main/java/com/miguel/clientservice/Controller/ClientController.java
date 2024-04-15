package com.miguel.clientservice.Controller;

import com.miguel.clientservice.Model.Client;
import com.miguel.clientservice.Service.ClientService;
import com.miguel.clientservice.Service.ClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final ClientServiceImpl clientServiceImpl;

    @PostMapping
    public Mono<ResponseEntity<Client>> saveClient(@RequestBody Client client) {
        return clientService.save(client).map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Client>> findById(@RequestParam String id) {
        return clientService.findById(id).map(ResponseEntity::ok);
    }

    @PostMapping("/id")
    public Mono<ResponseEntity<Client>> updateClient(@RequestBody Client client) {
        return clientService.findById(client.getDNI())
                .flatMap(p -> {
                    Client clientUpdate = Client.builder()
                            .DNI(client.getDNI())
                            .nombres(client.getNombres() == null ? p.getNombres() : client.getNombres())
                            .apellidos(client.getApellidos() == null ? p.getApellidos() : client.getApellidos())
                            .fechaNacimiento(client.getFechaNacimiento() == null ? p.getFechaNacimiento() : client.getFechaNacimiento())
                            .build();
                    return clientService.save(clientUpdate).map(ResponseEntity::ok);
                })
                .switchIfEmpty(clientService.save(client).map(ResponseEntity::ok));

    }

}
