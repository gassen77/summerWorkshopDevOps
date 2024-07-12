package com.example.devopstestsunitairesfacturation.repositories;

import com.example.devopstestsunitairesfacturation.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
}
