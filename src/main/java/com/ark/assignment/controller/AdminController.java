package com.ark.assignment.controller;

import com.ark.assignment.api.AdminApi;
import com.ark.assignment.models.Client;
import com.ark.assignment.models.NewClientRequest;
import com.ark.assignment.models.UpdateClientRequest;
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
    public ResponseEntity<Client> createNewClient(NewClientRequest newClientRequest) {

        Client c = clientService.create(newClientRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(c.getId()).toUri();
        return ResponseEntity.created(location).body(c);

    }

    @Override
    public ResponseEntity<Client> getClient(Long clientId) {
        return ResponseEntity.ok(clientService.findById(clientId));
    }

    @Override
    public ResponseEntity<Client> updateClient(Long clientId, UpdateClientRequest updateClientRequest) {
        return ResponseEntity.ok(clientService.updateClient(clientId, updateClientRequest));
    }

    @Override
    public ResponseEntity<Void> deleteClient(Long clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.ok().build();
    }
}
