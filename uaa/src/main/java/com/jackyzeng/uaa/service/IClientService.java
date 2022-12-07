package com.jackyzeng.uaa.service;

import com.jackyzeng.uaa.model.Client;

public interface IClientService {

    Client loadClientByClientId(String clientId);

    void saveClient(Client client);

    void deleteClient(long id);
}
