package com.ark.assignment.service;

import com.ark.assignment.models.Client;
import com.ark.assignment.models.NewClientRequest;

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
}
