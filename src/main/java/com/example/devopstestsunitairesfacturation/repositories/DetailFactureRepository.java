package com.example.devopstestsunitairesfacturation.repositories;

import com.example.devopstestsunitairesfacturation.entities.DetailFacture;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DetailFactureRepository extends JpaRepository<DetailFacture, Long> {
}
