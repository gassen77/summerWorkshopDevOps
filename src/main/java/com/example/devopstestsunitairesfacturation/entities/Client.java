package com.example.devopstestsunitairesfacturation.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idClient; // Clé primaire
    String nom;
    String prenom;
    LocalDate dateNaissance ;
    @Enumerated(EnumType.STRING)
    TrancheAge trancheAge;
    @OneToMany(mappedBy = "client")
    List<Facture> factures;
}
