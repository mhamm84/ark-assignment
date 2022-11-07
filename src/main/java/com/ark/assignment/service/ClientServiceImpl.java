package com.ark.assignment.service;

import com.ark.assignment.entity.ClientEntity;
import com.ark.assignment.entity.FundEntity;
import com.ark.assignment.exception.ClientNotFoundException;
import com.ark.assignment.models.Client;
import com.ark.assignment.models.Fund;
import com.ark.assignment.models.NewClientRequest;
import com.ark.assignment.models.UpdateClientRequest;
import com.ark.assignment.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public Client create(NewClientRequest newClientRequest) {
        log.info("newClientRequest: " + newClientRequest);

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName(newClientRequest.getName());

        clientEntity = clientRepository.save(clientEntity);
        return getClient(clientEntity);
    }

    @Override
    public Client findById(Long clientId) {
        log.info("findById: " + clientId);
        ClientEntity clientEntity = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(String.format("no client with the id of: %d", clientId)));
        return getClient(clientEntity);
    }

    @Override
    public Client updateClient(Long clientId, UpdateClientRequest updateClientRequest) {

        ClientEntity toUpdate = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(String.format("no client with the id of: %d", clientId)));
        toUpdate.setName(updateClientRequest.getName());
        toUpdate = clientRepository.save(toUpdate);
        return getClient(toUpdate);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    private Client getClient(ClientEntity clientEntity) {
        Client modelClient = new Client();
        modelClient.setName(clientEntity.getName());
        modelClient.setId(clientEntity.getId());
        return modelClient;
    }
}
