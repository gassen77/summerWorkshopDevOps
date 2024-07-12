package com.example.devopstestsunitairesfacturation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DetailFacture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idDetailFacture;
    Long quantite;
    Float totalDetail;
    @ManyToOne
    Produit produit;
    @ManyToOne
    @JsonIgnore
    Facture facture;
}
