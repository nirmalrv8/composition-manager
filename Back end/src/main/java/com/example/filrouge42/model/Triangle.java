package com.example.filrouge42.model;

import jakarta.persistence.*;

@Entity
@Table(name = "triangles") // Nom de la table dans la base de données
public class Triangle extends FormeGeometrique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cote") // Nom de la colonne dans la table
    private double cote;

    // Constructeur par défaut requis par Hibernate
    public Triangle() {
    }

    // Constructeur
    public Triangle(double cote, double rotation, Point position) {
        super(TypeForme.TRIANGLE, rotation, position);
        this.cote = cote;
    }

    public Triangle(double cote) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCote(double cote) {
        this.cote = cote;
    }

    public double getCote() {
        return cote;
    }

    // Implémentation des méthodes abstraites
    @Override
    public double calculerAire() {
        // Utilisez la formule de Heron pour calculer l'aire d'un triangle équilatéral
        double s = 3 * cote / 2;  // semi-périmètre pour le triangle équilatéral
        return Math.sqrt(s * Math.pow((s - cote), 3));
    }

    @Override
    public double calculerPerimetre() {
        return 3 * cote;
    }
}
