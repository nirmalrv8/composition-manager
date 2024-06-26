package com.example.filrouge42.model;


import jakarta.persistence.*;

@Entity
@Table(name = "carres")
public class Carre extends FormeGeometrique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "cote")
    private double cote;

    // Constructeur par défaut requis par Hibernate
    public Carre() {
        super();
    }

    public Carre(double cote,double rotation, Point position) {
        super(TypeForme.CARRE,rotation, position);
        this.cote = cote;
    }

    public Carre(double cote) {
        super(TypeForme.CARRE, 0.0, new Point(0.0, 0.0)); // Default rotation and position
        this.cote = cote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCote() {
        return cote;
    }

    public void setCote(double cote) {
        this.cote = cote;
    }

    // Implémentation des méthodes abstraites
    @Override
    public double calculerAire() {
        return cote * cote;
    }

    @Override
    public double calculerPerimetre() {
        return 4 * cote;
    }
}
