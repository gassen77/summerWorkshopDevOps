package com.example.devopstestsunitairesfacturation.services;

import com.example.devopstestsunitairesfacturation.entities.Client;

public interface IClientService {

    Client ajouterClient (Client c);
    void supprimerClient(Long idClient);

}
