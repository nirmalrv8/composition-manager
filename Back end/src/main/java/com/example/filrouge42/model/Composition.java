package com.example.filrouge42.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Composition {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<FormeGeometrique> formes = new ArrayList<>();

    @Transient private double aire;
    @Transient private double perimetre;

    public Composition() {}

    // Méthode pour ajouter une forme à la liste
    public void ajouterForme(FormeGeometrique forme) {
        formes.add(forme);
    }

    // Méthode pour retirer une forme de la liste
    public void retirerForme(FormeGeometrique forme) {
        formes.remove(forme);
    }

    // Méthode pour calculer l'aire totale
    public double calculerAireTotale() {
        double aireTotale = 0.0;
        for (FormeGeometrique forme : formes) {
            aireTotale += forme.calculerAire();
        }
        return Math.round(aireTotale * 100.0) / 100.0;
    }

    // Méthode pour calculer le périmètre total
    public double calculerPerimetreTotal() {
        double perimetreTotal = 0.0;
        for (FormeGeometrique forme : formes) {
            perimetreTotal += forme.calculerPerimetre();
        }
        return Math.round(perimetreTotal * 100.0) / 100.0;
    }

    // Méthode pour sauvegarder la composition (simulée)
    public void sauvegarderComposition() {
        // Logique pour sauvegarder la composition
        System.out.println("Composition sauvegardée avec succès.");
    }

    // Méthode pour charger la composition (simulée)
    public void chargerComposition() {
        // Logique pour charger la composition
        System.out.println("Composition chargée avec succès.");
    }

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FormeGeometrique> getFormes() {
        return formes;
    }

    public void setFormes(List<FormeGeometrique> formes) {
        this.formes = formes;
    }

    public double getAire() {
        return aire;
    }

    public void setAire(double aire) {
        this.aire = aire;
    }
    
    public double getPerimetre() {
        return perimetre;
    }

    public void setPerimetre(double perimetre) {
        this.perimetre = perimetre;
    }
}
