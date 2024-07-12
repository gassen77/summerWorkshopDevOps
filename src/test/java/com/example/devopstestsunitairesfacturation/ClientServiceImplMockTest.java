package com.example.devopstestsunitairesfacturation;

import com.example.devopstestsunitairesfacturation.entities.Client;
import com.example.devopstestsunitairesfacturation.entities.Facture;
import com.example.devopstestsunitairesfacturation.entities.TrancheAge;
import com.example.devopstestsunitairesfacturation.repositories.ClientRepository;
import com.example.devopstestsunitairesfacturation.services.ClientServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest


@Slf4j
public class ClientServiceImplMockTest {

    @Autowired
    ClientServiceImpl clientService;
    @MockBean
    ClientRepository clientRepository;
    /* ce test permet de vérifier que le nom et le prénom
    * du client ainsi que la tranche d'age est bien associé à son age
    * age < 10 : enfant entre 10 et 19 : adolesent et supérieur à 19 adule
    * Nous vérifions aussi que le client a bien été ajouté à la table */
    @Test
    public void testAjouterClient() {
        // Création client avec un age qui le classifie dans la trancheAge adulte
        Client adultClient=  Client.builder().nom("bouslama").prenom("ahmed")
                .dateNaissance(LocalDate.parse("1990-04-01")).build();
          // Ajout Client
        Mockito.when(clientRepository.save(Mockito.any(Client.class)))
                .thenReturn(adultClient);
        Client savedClient = clientService.ajouterClient(adultClient);
        TrancheAge expectedTrancheAge = TrancheAge.ADULTE;
        // vérifier que le client est associé à la bonne tranche d'age
        Assertions.assertEquals(expectedTrancheAge, savedClient.getTrancheAge());
        // vérifier que la taille des noms et prénoms est logique
        Assertions.assertTrue(savedClient.getNom().length()<15);
        Assertions.assertTrue(savedClient.getPrenom().length()<10);
        verify(clientRepository).save(Mockito.any(Client.class));
    }
}
