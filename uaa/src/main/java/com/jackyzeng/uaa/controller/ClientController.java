package com.jackyzeng.uaa.controller;

import com.jackyzeng.uaa.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    private IClientService clientService;


}
