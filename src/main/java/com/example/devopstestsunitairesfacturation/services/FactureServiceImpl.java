package com.example.devopstestsunitairesfacturation.services;

import com.example.devopstestsunitairesfacturation.entities.DetailFacture;
import com.example.devopstestsunitairesfacturation.entities.Facture;
import com.example.devopstestsunitairesfacturation.entities.Produit;
import com.example.devopstestsunitairesfacturation.repositories.ClientRepository;
import com.example.devopstestsunitairesfacturation.repositories.DetailFactureRepository;
import com.example.devopstestsunitairesfacturation.repositories.FactureRepository;
import com.example.devopstestsunitairesfacturation.repositories.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class FactureServiceImpl implements IFactureService{

    FactureRepository factureRepository;
    ClientRepository clientRepository;
    ProduitRepository produitRepository;
    DetailFactureRepository detailFactureRepository;

    @Override
    public Facture ajouterFacture(Facture f) {
        // récupérer les détails pour les mettre à jour
        List<DetailFacture> detailFactures =  f.getDetailsFacture();
        // initialiser le totalttc facture à zéro
        AtomicReference<Float> total = new AtomicReference<>(Float.valueOf(0));
        detailFactures.stream().forEach(detailFacture ->
        {
            // récupérer un par un le produit de chaque detail
            Produit p = produitRepository.findById(detailFacture.getProduit().getIdProduit()).get();
            // calculer le total de la ligne de commande : prix unitaire * qte
            detailFacture.setTotalDetail(p.getPrixUnitaire()* detailFacture.getQuantite());
            total.set(total.get() + detailFacture.getTotalDetail());
        });
        // affecter les details facture mis à jour
        f.setDetailsFacture(detailFactures);
        f.setTotalFactureHt(total.get());
        // calcul du total avec remise
        f.setTotalFactureHtRemise(f.getTotalFactureHt()- (f.getTotalFactureHt()*f.getRemise()/100));
        // sauvegarde des nouveaux valeurs de la facture ainsi que ses détails mis à jour
        Facture savedFacture = factureRepository.save(f);
        detailFactures.stream().forEach(
                detailFacture -> {
                    detailFacture.setFacture(f);
                    detailFactureRepository.save(detailFacture);
                }
        );
        return f;
        }

    @Override
    public void supprimerFacture(Long idFacture) {
        factureRepository.deleteById(idFacture);
    }
}
