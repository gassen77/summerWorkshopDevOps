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
public class Facture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idFacture;
    Float totalFactureHt;
    Long remise;
    Float totalFactureHtRemise;
    @ManyToOne
    Client client;
    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
    List<DetailFacture> detailsFacture;
}
