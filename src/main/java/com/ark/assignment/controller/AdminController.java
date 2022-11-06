package com.ark.assignment.controller;

import com.ark.assignment.api.AdminApi;
import com.ark.assignment.models.Client;
import com.ark.assignment.models.NewClientRequest;
import com.ark.assignment.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AdminController implements AdminApi {

    private final ClientService clientService;

    @Override
    public ResponseEntity<Void> createNewClient(NewClientRequest newClientRequest) {

        Client c = clientService.create(newClientRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(c.getId()).toUri();
        return ResponseEntity.created(location)
                //.body(offer)
                .build();

    }

    @Override
    public ResponseEntity<Client> getClient(Long clientId) {
        Client c = new Client();
        c.setId(1L);
        c.setName("Test Client!");

        return ResponseEntity.ok(c);
    }
}
