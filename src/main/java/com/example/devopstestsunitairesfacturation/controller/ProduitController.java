package com.example.devopstestsunitairesfacturation.controller;

import com.example.devopstestsunitairesfacturation.entities.Produit;
import com.example.devopstestsunitairesfacturation.services.IProduitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/produit")

public class ProduitController {
    IProduitService produitService;

    // http://localhost:8089/facturation/produit/add-produit
    @PostMapping("/add-produit")
    @ResponseBody
    public Produit addProduit(@RequestBody Produit p) {
        Produit produit= produitService.ajouterProduit(p);
        return produit;
    }

}
