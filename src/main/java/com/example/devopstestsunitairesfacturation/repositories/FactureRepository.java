package com.example.devopstestsunitairesfacturation.repositories;

import com.example.devopstestsunitairesfacturation.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture,Long> {
}
