package com.example.devopstestsunitairesfacturation.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idProduit;
    Float prixUnitaire;
    String code;
    String libelle;
    @OneToMany(mappedBy = "produit")
    List<DetailFacture> detailFactures;
}
