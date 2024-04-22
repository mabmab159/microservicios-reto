package com.miguel.clientservicequery.Controller;

import com.miguel.clientservicequery.Model.Client;
import com.miguel.clientservicequery.Model.ValidateResponse;
import com.miguel.clientservicequery.Service.ClientService;
import com.miguel.clientservicequery.Service.UserFeignService;
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

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Client>> findById(@RequestParam String id, @RequestHeader(value = "Authorization") String token) {
        ValidateResponse validateResponse = userFeignService.validateToken(token);
        List<String> roles = validateResponse.getRoles();
        if (roles.contains("GET") && validateResponse.getSuccess()) {
            return clientService.findById(id).map(ResponseEntity::ok);
        }
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
