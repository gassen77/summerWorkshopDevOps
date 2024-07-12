package com.example.devopstestsunitairesfacturation.services;

import com.example.devopstestsunitairesfacturation.entities.Facture;

public interface IFactureService {
    Facture ajouterFacture(Facture f);
  void supprimerFacture(Long idFacture);
}
