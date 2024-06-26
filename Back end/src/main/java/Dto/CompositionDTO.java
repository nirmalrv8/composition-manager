package Dto;

import java.util.List;

public class CompositionDTO {
    private Long id;
    private UtilisateurDTO utilisateur;
    private List<FormeGeometriqueDTO> formes;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UtilisateurDTO getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurDTO utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<FormeGeometriqueDTO> getFormes() {
        return formes;
    }

    public void setFormes(List<FormeGeometriqueDTO> formes) {
        this.formes = formes;
    }
}
