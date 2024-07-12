package com.example.devopstestsunitairesfacturation.services;

import com.example.devopstestsunitairesfacturation.configuration.AgeUtility;
import com.example.devopstestsunitairesfacturation.entities.Client;
import com.example.devopstestsunitairesfacturation.entities.TrancheAge;
import com.example.devopstestsunitairesfacturation.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ClientServiceImpl implements IClientService{

    ClientRepository clientRepository;

    @Override
    public Client ajouterClient(Client c) {
        Client client = currentAgeAffectation(c);
        return clientRepository.save(client);
    }
    private Client currentAgeAffectation(Client c) {
        int currentAge = AgeUtility.calculateAge(c.getDateNaissance());
        if(currentAge<10)
        { c.setTrancheAge(TrancheAge.ENFANT); }
        else if(currentAge>=10 && currentAge < 19)
        { c.setTrancheAge(TrancheAge.ADOLESCENT); }
        else { c.setTrancheAge(TrancheAge.ADULTE);}
        return c;
    }


    @Override
    public void supprimerClient(Long idClient) {
         clientRepository.deleteById(idClient);
    }
}
