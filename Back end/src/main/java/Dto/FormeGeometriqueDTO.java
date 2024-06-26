package Dto;

import com.example.filrouge42.model.*;

public class FormeGeometriqueDTO {

    private long id;

    private String typeForme;
    private Point position;

    private double longueur; // Pour le rectangle
    private double largeur; // Pour le rectangle
    private double rotation;

    private double cote; // Pour le triangle
    private double rayon; // Pour le cercle

    public FormeGeometriqueDTO() {
    }

    public FormeGeometriqueDTO(FormeGeometrique formeGeometrique) {
        this.typeForme = formeGeometrique.getTypeForme().toString();
        if (formeGeometrique.getTypeForme() == TypeForme.RECTANGLE) {
            this.longueur = ((Rectangle) formeGeometrique).getLongueur();
            this.largeur = ((Rectangle) formeGeometrique).getLargeur();
        } else if (formeGeometrique.getTypeForme() == TypeForme.TRIANGLE) {
            this.cote = ((Triangle) formeGeometrique).getCote();
        } else if (formeGeometrique.getTypeForme() == TypeForme.CERCLE) {
            this.rayon = ((Cercle) formeGeometrique).getRayon();
        }
    }

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeForme() {
        return typeForme;
    }

    public void setTypeForme(String typeForme) {
        this.typeForme = typeForme;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getCote() {
        return cote;
    }

    public void setCote(double cote) {
        this.cote = cote;
    }

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }
}
