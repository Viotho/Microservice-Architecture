package com.jackyzeng.uaa.service.impl;

import com.jackyzeng.uaa.model.Client;
import com.jackyzeng.uaa.service.IClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements IClientService {

    @Override
    public Client loadClientByClientId(String clientId) {
        return null;
    }

    @Override
    public void saveClient(Client client) {

    }

    @Override
    public void deleteClient(long id) {

    }
}
