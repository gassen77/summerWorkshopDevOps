package com.example.devopstestsunitairesfacturation.services;

import com.example.devopstestsunitairesfacturation.entities.Produit;

import java.util.List;

public interface IProduitService {
    Produit ajouterProduit(Produit produit);
    void supprimerProduit(Long idProduit);
}
