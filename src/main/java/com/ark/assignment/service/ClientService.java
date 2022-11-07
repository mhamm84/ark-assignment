package com.ark.assignment.service;

import com.ark.assignment.models.Client;
import com.ark.assignment.models.NewClientRequest;
import com.ark.assignment.models.UpdateClientRequest;

public interface ClientService {

    /**
     * Creates a new Client
     *
     * @param newClientRequest
     * @return
     */
    Client create(NewClientRequest newClientRequest);

    /**
     * Find a Client by Id
     *
     * @param clientsId
     * @return
     */
    Client findById(Long clientsId);

    /**
     * Update a Client
     *
     * @param updateClientRequest
     * @return
     */
    Client updateClient(Long clientId, UpdateClientRequest updateClientRequest);

    /**
     * Delete Client with id
     *
     * @param clientId
     */
    void deleteClient(Long clientId);
}
