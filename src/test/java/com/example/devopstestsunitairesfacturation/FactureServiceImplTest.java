package com.example.devopstestsunitairesfacturation;

import com.example.devopstestsunitairesfacturation.entities.*;
import com.example.devopstestsunitairesfacturation.services.IClientService;
import com.example.devopstestsunitairesfacturation.services.IFactureService;
import com.example.devopstestsunitairesfacturation.services.IProduitService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
public class FactureServiceImplTest {

  @Autowired
    IFactureService factureService;
  @Autowired
    IProduitService produitService;
  @Autowired
    IClientService clientService;

    @Test
    @Order(0) // en cas de plusieurs tests, nous pouvons les ordonner selon un enchainement bien précis

    /* le test de l'ajout facture consiste à vérifier que le total de chaque
     * détail facture ainsi que le total global de la facture soit bien calculé
     * en prenant en considération le prix du produit, la quantité et le pourcentage
     * de la remise.Nous vérifions aussi que la facture et ses détails
     * sont bien insérés dans la base de donnée*/
    public void testAjouterFacture() {
        //Création Client
        Client client = Client.builder().prenom("ahmed").nom("slimi")
                .dateNaissance(LocalDate.parse("2000-05-01")).
                trancheAge(TrancheAge.ADULTE).build();
        // sauvegarde client
        Client savedClient= clientService.ajouterClient(client);
        // création des produits
        Produit produit1 = Produit.builder().libelle("dentifrice")
                .code("05").prixUnitaire(Float.valueOf(25)).build();
        Produit produit2 = Produit.builder().libelle("pèse personne")
                .code("19").prixUnitaire(Float.valueOf(250)).build();
        // sauvegarde des produits
        Produit savedProduct1= produitService.ajouterProduit(produit1);
        Produit savedProduct2= produitService.ajouterProduit(produit2);
        // création des détails factures
        DetailFacture detailFacture1 = DetailFacture.builder().
        produit(savedProduct1).quantite(2L).build();
        DetailFacture detailFacture2 = DetailFacture.builder().
                produit(savedProduct2).quantite(1L).build();
        List<DetailFacture> detailFactures = new ArrayList<>();
        detailFactures.add(detailFacture1);
        detailFactures.add(detailFacture2);
        //Création de la facture associé aux client, produits et détails déja crées
        Facture facture = Facture.builder().client(savedClient)
                .remise(10L).detailsFacture(detailFactures).build();
        // sauvegarder la facture
       Facture persistedFacture = factureService.ajouterFacture(facture);
       // vérifier que la facture et ses détails ont bien été ajoutées dans la base
       Assertions.assertNotNull(persistedFacture.getIdFacture());
       persistedFacture.getDetailsFacture().forEach(detailFacture ->
       {
           Assertions.assertNotNull(detailFacture.getIdDetailFacture());
       });
     // totalFactureHtRemise  = (25*2 +250) *0.9 = 270 -- réduction remise 10%
       Float expectedTotalFacture = Float.valueOf(270);
        Assertions.assertEquals(expectedTotalFacture,persistedFacture.getTotalFactureHtRemise());
       // nettoyer la base de donnée en supprimant toutes les données insérées dans ce test
        // supprimer la facture avec ses détails (cascade)
        factureService.supprimerFacture(persistedFacture.getIdFacture());
       // supprimer les produits
         produitService.supprimerProduit(produit1.getIdProduit());
        produitService.supprimerProduit(produit2.getIdProduit());
       // supprimer le client
        clientService.supprimerClient(client.getIdClient());
    } }
