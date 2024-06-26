// CompositionsController.java
package com.example.filrouge42.Contoller;

import com.example.filrouge42.Services.CompositionsService;
import com.example.filrouge42.Services.FormesService;
import com.example.filrouge42.model.Carre;
import com.example.filrouge42.model.Cercle;
import com.example.filrouge42.model.Composition;
import com.example.filrouge42.model.FormeGeometrique;
import com.example.filrouge42.model.Rectangle;
import com.example.filrouge42.model.Triangle;
import com.example.filrouge42.model.TypeForme;

import Dto.CompositionDTO;
import Dto.FormeGeometriqueDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/compositions")
@CrossOrigin("*")
public class CompositionsController {

    @Autowired
    private CompositionsService compositionsService;

    public CompositionsController(CompositionsService compositionsService, FormesService formesService) {
        this.compositionsService = compositionsService;
    }

    @GetMapping
    public List<Composition> getAllCompositions() {
        return compositionsService.getAllCompositions();
    }

    @GetMapping("/{id}")
    public Composition getCompositionbyID(@PathVariable Long id) {
        return compositionsService.getCompositionbyID(id);
    }

    @PostMapping
    public Composition createComposition(@RequestBody CompositionDTO compositionDTO) {
        Composition composition = new Composition();
        List<FormeGeometrique> formes = new ArrayList<>();

        List<FormeGeometriqueDTO> creatingFormList = compositionDTO.getFormes();
        for (FormeGeometriqueDTO creatingForm : creatingFormList) {
            if (creatingForm.getTypeForme().equals("RECTANGLE")) {
                Rectangle rectangle = new Rectangle();
                rectangle.setTypeForme(TypeForme.valueOf(creatingForm.getTypeForme()));
                rectangle.setPosition(creatingForm.getPosition());
                rectangle.setRotation(creatingForm.getRotation());
                rectangle.setLargeur(creatingForm.getLargeur());
                rectangle.setLongueur(creatingForm.getLongueur());
                formes.add(rectangle);
            } else if (creatingForm.getTypeForme().equals("TRIANGLE")) {
                Triangle triangle = new Triangle();
                triangle.setTypeForme(TypeForme.valueOf(creatingForm.getTypeForme()));
                triangle.setPosition(creatingForm.getPosition());
                triangle.setRotation(creatingForm.getRotation());
                triangle.setCote(creatingForm.getCote());
                formes.add(triangle);
            } else if (creatingForm.getTypeForme().equals("CERCLE")) {
                Cercle cercle = new Cercle();
                cercle.setTypeForme(TypeForme.valueOf(creatingForm.getTypeForme()));
                cercle.setPosition(creatingForm.getPosition());
                cercle.setRotation(creatingForm.getRotation());
                cercle.setRayon(creatingForm.getRayon());
                formes.add(cercle);
            } else if (creatingForm.getTypeForme().equals("CARRE")) {
                Carre carre = new Carre();
                carre.setTypeForme(TypeForme.valueOf(creatingForm.getTypeForme()));
                carre.setPosition(creatingForm.getPosition());
                carre.setRotation(creatingForm.getRotation());
                carre.setCote(creatingForm.getCote());
                formes.add(carre);
            }
        }

        composition.setFormes(formes);
        return compositionsService.createComposition(composition);
    }

    @PostMapping("/{compositionId}/ajouter-forme/{formeId}")
    public void addFormeToComposition(@PathVariable long compositionId, @RequestBody FormeGeometrique forme) {
        compositionsService.addFormeToComposition(compositionId, forme);
    }

    @GetMapping("/{compositionId}/formes")
    public List<FormeGeometrique> getFormesInComposition(@PathVariable long compositionId) {
        return compositionsService.getFormsInComposition(compositionId);
    }

    @PutMapping
    public Composition updateComposition(@RequestBody CompositionDTO modifiedComposition) {
        Composition composition = this.compositionsService.getCompositionbyID(modifiedComposition.getId());
        List<FormeGeometrique> formes = new ArrayList<>();
        
        List<FormeGeometriqueDTO> modifiedFormList = modifiedComposition.getFormes();
        for (FormeGeometriqueDTO modifiedForm : modifiedFormList) {
            if (modifiedForm.getTypeForme().equals("RECTANGLE")) {
                Rectangle rectangle = new Rectangle();
                rectangle.setId(modifiedForm.getId());
                rectangle.setTypeForme(TypeForme.valueOf(modifiedForm.getTypeForme()));
                rectangle.setPosition(modifiedForm.getPosition());
                rectangle.setRotation(modifiedForm.getRotation());
                rectangle.setLargeur(modifiedForm.getLargeur());
                rectangle.setLongueur(modifiedForm.getLongueur());
                formes.add(rectangle);
            } else if (modifiedForm.getTypeForme().equals("TRIANGLE")) {
                Triangle triangle = new Triangle();
                triangle.setId(modifiedForm.getId());
                triangle.setTypeForme(TypeForme.valueOf(modifiedForm.getTypeForme()));
                triangle.setPosition(modifiedForm.getPosition());
                triangle.setRotation(modifiedForm.getRotation());
                triangle.setCote(modifiedForm.getCote());
                formes.add(triangle);
            } else if (modifiedForm.getTypeForme().equals("CERCLE")) {
                Cercle cercle = new Cercle();
                cercle.setId(modifiedForm.getId());
                cercle.setTypeForme(TypeForme.valueOf(modifiedForm.getTypeForme()));
                cercle.setPosition(modifiedForm.getPosition());
                cercle.setRotation(modifiedForm.getRotation());
                cercle.setRayon(modifiedForm.getRayon());
                formes.add(cercle);
            } else if (modifiedForm.getTypeForme().equals("CARRE")) {
                Carre carre = new Carre();
                carre.setId(modifiedForm.getId());
                carre.setTypeForme(TypeForme.valueOf(modifiedForm.getTypeForme()));
                carre.setPosition(modifiedForm.getPosition());
                carre.setRotation(modifiedForm.getRotation());
                carre.setCote(modifiedForm.getCote());
                formes.add(carre);
            }
        }

        composition.setFormes(formes);
        return this.compositionsService.createComposition(composition);
    }

    @DeleteMapping("/{compositionId}")
    public void removeFormesFromComposition(@PathVariable long compositionId) {
        this.compositionsService.deleteCompositionById(compositionId);
    }
}
