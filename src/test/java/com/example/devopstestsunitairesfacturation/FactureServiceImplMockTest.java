package com.example.devopstestsunitairesfacturation;

import com.example.devopstestsunitairesfacturation.entities.*;
import com.example.devopstestsunitairesfacturation.repositories.DetailFactureRepository;
import com.example.devopstestsunitairesfacturation.repositories.FactureRepository;
import com.example.devopstestsunitairesfacturation.repositories.ProduitRepository;
import com.example.devopstestsunitairesfacturation.services.IClientService;
import com.example.devopstestsunitairesfacturation.services.IFactureService;
import com.example.devopstestsunitairesfacturation.services.IProduitService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
public class FactureServiceImplMockTest {

  @Autowired
    IFactureService factureService;
  @Autowired
    IProduitService produitService;
  @Autowired
    IClientService clientService;

    @MockBean
    FactureRepository factureRepository;
    @MockBean
    ProduitRepository produitRepository;
    @MockBean
    DetailFactureRepository detailFactureRepository;
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
                trancheAge(TrancheAge.ADOLESCENT).build();
        // création des produits
        Produit produit1 = Produit.builder().idProduit(1L).libelle("dentifrice")
                .code("05").prixUnitaire(Float.valueOf(25)).build();
        Produit produit2 = Produit.builder().idProduit(2L).libelle("pèse personne")
                .code("19").prixUnitaire(Float.valueOf(250)).build();
        // création des détails factures
        DetailFacture detailFacture1 = DetailFacture.builder().
        produit(produit1).quantite(2L).build();
        DetailFacture detailFacture2 = DetailFacture.builder().
                produit(produit2).quantite(1L).build();
        List<DetailFacture> detailFactures = new ArrayList<>();
        detailFactures.add(detailFacture1);
        detailFactures.add(detailFacture2);
        //Création de la facture associé aux client, produits et détails déja crées
        Facture facture = Facture.builder().client(client)
                .remise(10L).detailsFacture(detailFactures).build();
      // Initialiser le résultat d'appel aux repos par des données mock
        Mockito.when(factureRepository.save(Mockito.any(Facture.class))).thenReturn(facture);
        Mockito.when(produitRepository.findById(1L)).thenReturn(Optional.ofNullable(produit1));
        Mockito.when(produitRepository.findById(2L)).thenReturn(Optional.ofNullable(produit2));
        Mockito.when(detailFactureRepository.save(detailFacture1)).thenReturn(detailFacture1);
        Mockito.when(detailFactureRepository.save(detailFacture2)).thenReturn(detailFacture2);
        // faire appel à la méthode de sauvegarder facture
        Facture mockedFacture = factureService.ajouterFacture(facture);
        // totalFactureHtRemisé = (25*2 +250) *0.9 = 270 -- réduction remise 10%
        Float expectedTotalFacture = Float.valueOf(270);
        Assertions.assertEquals(expectedTotalFacture,mockedFacture.getTotalFactureHtRemise());
        // vérifier l'appel à la méthode save du facture repository
        verify(factureRepository).save(Mockito.any(Facture.class)); }}
