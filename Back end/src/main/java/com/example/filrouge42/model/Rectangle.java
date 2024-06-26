package com.example.filrouge42.model;

import com.example.filrouge42.model.FormeGeometrique;
import com.example.filrouge42.model.Point;
import jakarta.persistence.*;

@Entity
@Table(name = "rectangles")
public class Rectangle extends FormeGeometrique {

    @Column  (name = "longueur")
    private double longueur;
    @Column (name = "largeur")
    private double largeur;
    @Id
    private Long id;

    public Rectangle(){}

    // Constructor
    public Rectangle(double longueur, double largeur, double rotation, Point position) {
        super(TypeForme.RECTANGLE, rotation, position);
        this.longueur = longueur;
        this.largeur = largeur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Méthodes spécifiques à Rectangle
    public double getLongueur() {
        return longueur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    // Implémentation des méthodes abstraites
    @Override
    public double calculerAire() {
        return longueur * largeur;
    }

    @Override
    public double calculerPerimetre() {
        return 2 * (longueur + largeur);
    }
}
