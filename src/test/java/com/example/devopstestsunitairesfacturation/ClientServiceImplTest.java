package com.example.devopstestsunitairesfacturation;

import com.example.devopstestsunitairesfacturation.entities.Client;
import com.example.devopstestsunitairesfacturation.entities.TrancheAge;
import com.example.devopstestsunitairesfacturation.services.ClientServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest


@Slf4j
public class ClientServiceImplTest {

    @Autowired
    ClientServiceImpl clientService;
    /* ce test permet de vérifier que le nom et le prénom
    * du client ainsi que la tranche d'age est bien associé à son age
    * age < 10 : enfant entre 10 et 19 : adolesent et supérieur à 19 adule
    * Nous vérifions aussi que le client a bien été ajouté à la table */
    @Test
    public void testAjouterClient() {

        // création client avec un age qui le classifie dans la trancheAge adulte
        Client adultClient=  Client.builder().nom("bouslama").prenom("ahmed")
                .dateNaissance(LocalDate.parse("1990-04-01")).build();
        //   Client adolesentClient = Client.builder().nom("hmidi").prenom("salma").dateNaissance(LocalDate.parse("2010-05-05")).build();
        // Client enfantClient=  Client.builder().nom("ayari").prenom("hedi").dateNaissance(LocalDate.parse("2020-11-02")).build();
        // Ajout Client
        Client savedClient = clientService.ajouterClient(adultClient);
        // Vérifier que le client a bien été ajouté dans la table client
        Assertions.assertNotNull(savedClient.getIdClient());
        TrancheAge expectedTrancheAge = TrancheAge.ADULTE;
        // vérifier que le client est associé à la bonne tranche d'age
        Assertions.assertEquals(expectedTrancheAge, savedClient.getTrancheAge());
        // vérifier que la taille des noms et prénoms est logique
        Assertions.assertTrue(savedClient.getNom().length()<15);
        Assertions.assertTrue(savedClient.getPrenom().length()<10);
        // nettoyer la base
        clientService.supprimerClient(savedClient.getIdClient());
    }
}
