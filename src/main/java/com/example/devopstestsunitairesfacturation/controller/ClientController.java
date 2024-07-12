package com.example.devopstestsunitairesfacturation.controller;

import com.example.devopstestsunitairesfacturation.entities.Client;
import com.example.devopstestsunitairesfacturation.services.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/client")

public class ClientController {

    IClientService clientService;

    // http://localhost:8089/facturation/client/add-client
    @PostMapping("/add-client")
    @ResponseBody
    public Client addClient(@RequestBody Client c) {
        Client client= clientService.ajouterClient(c);
        return client;
    }

}
