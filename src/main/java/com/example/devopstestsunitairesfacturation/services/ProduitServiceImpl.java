package com.example.devopstestsunitairesfacturation.services;

import com.example.devopstestsunitairesfacturation.entities.Produit;
import com.example.devopstestsunitairesfacturation.repositories.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProduitServiceImpl implements IProduitService{

    ProduitRepository produitRepository;

    @Override
    public Produit ajouterProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public void supprimerProduit(Long idProduit) {
        produitRepository.deleteById(idProduit);
    }


}
