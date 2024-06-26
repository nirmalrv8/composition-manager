package Dto;

import Dto.FormeGeometriqueDTO;

import java.util.List;

public class FormeCollectionDTO {
    private Long id;
    private List<FormeGeometriqueDTO> formes;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FormeGeometriqueDTO> getFormes() {
        return formes;
    }

    public void setFormes(List<FormeGeometriqueDTO> formes) {
        this.formes = formes;
    }
}
