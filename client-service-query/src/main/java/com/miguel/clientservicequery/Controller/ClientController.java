package com.miguel.clientservicequery.Controller;

import com.miguel.clientservicequery.Model.Client;
import com.miguel.clientservicequery.Service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Client>> findById(@RequestParam String id) {
        return clientService.findById(id).map(ResponseEntity::ok);
    }
}
