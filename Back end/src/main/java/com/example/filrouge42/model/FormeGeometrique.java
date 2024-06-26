package com.example.filrouge42.model;

import jakarta.persistence.*;

@Entity
@Table(name = "formes_geometriques")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class FormeGeometrique {

    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    protected TypeForme typeForme;
    private double rotation;

    @Embedded
    private Point position;

    public FormeGeometrique(){}

    // Constructeur
    public FormeGeometrique(TypeForme typeForme, double rotation, Point position) {
        this.typeForme = typeForme;
        this.rotation = rotation;
        this.position = position;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public double getRotation() {
        return rotation;
    }

    public Point getPosition() {
        return position;
    }

    public TypeForme getTypeForme() {
        return typeForme;
    }

    // Méthodes de modification
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setTypeForme(TypeForme typeForme) {
        this.typeForme = typeForme;
    }

    // Méthodes abstraites à implémenter par les classes dérivées
    public abstract double calculerAire();

    public abstract double calculerPerimetre();
}
