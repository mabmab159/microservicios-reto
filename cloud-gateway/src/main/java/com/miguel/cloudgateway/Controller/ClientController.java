package com.miguel.cloudgateway.Controller;

import com.miguel.cloudgateway.Model.Client;
import com.miguel.cloudgateway.Model.ValidateResponse;
import com.miguel.cloudgateway.Service.ClientService;
import com.miguel.cloudgateway.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable String id, @RequestHeader(value = "Authorization") String token) {
        ValidateResponse validateResponse = userService.validateToken(token);
        List<String> roles = validateResponse.getRoles();
        if (roles.contains("GET") && validateResponse.getSuccess()) {
            return ResponseEntity.ok(clientService.getClient(id));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client, @RequestHeader(value = "Authorization") String token) {
        ValidateResponse validateResponse = userService.validateToken(token);
        List<String> roles = validateResponse.getRoles();
        if (roles.contains("POST") && validateResponse.getSuccess()) {
            return ResponseEntity.ok(clientService.saveClient(client));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping
    public ResponseEntity<Client> updateClient(@RequestBody Client client, @RequestHeader(value = "Authorization") String token) {
        ValidateResponse validateResponse = userService.validateToken(token);
        List<String> roles = validateResponse.getRoles();
        if (roles.contains("PUT") && validateResponse.getSuccess()) {
            return ResponseEntity.ok(clientService.updateClient(client));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
