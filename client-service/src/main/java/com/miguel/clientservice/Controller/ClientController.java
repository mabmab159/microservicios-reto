package com.miguel.clientservice.Controller;

import com.miguel.clientservice.Model.Client;
import com.miguel.clientservice.Model.ValidateResponse;
import com.miguel.clientservice.Service.ClientService;
import com.miguel.clientservice.Service.UserFeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final UserFeignService userFeignService;

    @PostMapping
    public Mono<ResponseEntity<Client>> saveClient(@RequestBody Client client, @RequestHeader(value = "Authorization") String token) {
        ValidateResponse validateResponse = userFeignService.validateToken(token);
        List<String> roles = validateResponse.getRoles();
        if (roles.contains("POST") && validateResponse.getSuccess()) {
            return clientService.save(client).map(ResponseEntity::ok);
        }
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PatchMapping("/update")
    public Mono<ResponseEntity<Client>> updateClient(@RequestBody Client client, @RequestHeader(value = "Authorization") String token) {
        ValidateResponse validateResponse = userFeignService.validateToken(token);
        List<String> roles = validateResponse.getRoles();
        if (roles.contains("PATCH") && validateResponse.getSuccess()) {
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
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
