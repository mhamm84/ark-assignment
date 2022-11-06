package com.ark.assignment.service;

import com.ark.assignment.models.Client;
import com.ark.assignment.models.NewClientRequest;

public interface ClientService {

    Client create(NewClientRequest newClientRequest);
}
