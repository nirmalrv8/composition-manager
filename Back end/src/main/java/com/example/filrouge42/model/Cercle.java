package com.example.filrouge42.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table (name = "cercles")// Nom de la table dans la base de données
public class Cercle extends FormeGeometrique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "rayon")// Nom de la colonne dans la table
    private double rayon;

    // Constructeur par défaut requis par Hibernate
    public Cercle() {
        super();
    }

    //Constructeur
    public Cercle(double rayon, double rotation, Point position) {
        super(TypeForme.CERCLE, rotation, position);
        this.rayon = rayon;
    }

    public Cercle(double rayon) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRayon()
    {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    // Implémentation des méthodes abstraites
    @Override
    public double calculerAire() {
        return Math.PI * rayon * rayon;
    }

    @Override
    public double calculerPerimetre() {
        return 2 * Math.PI * rayon;
    }
}
