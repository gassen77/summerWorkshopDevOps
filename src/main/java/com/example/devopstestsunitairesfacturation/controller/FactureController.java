package com.example.devopstestsunitairesfacturation.controller;


import com.example.devopstestsunitairesfacturation.entities.Client;
import com.example.devopstestsunitairesfacturation.entities.Facture;
import com.example.devopstestsunitairesfacturation.services.IFactureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/facture")

public class FactureController {

    IFactureService factureService;
    // http://localhost:8089/facturation/facture/add-facture/1
    @PostMapping("/add-facture")
    @ResponseBody
    public Facture addFacture(@RequestBody Facture f) {
        Facture facture= factureService.ajouterFacture(f);
        return facture;
    }
}
