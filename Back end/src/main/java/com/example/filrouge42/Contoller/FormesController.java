package com.example.filrouge42.Contoller;

import Dto.FormeGeometriqueDTO;
import com.example.filrouge42.Services.FormesServiceImpl;
import com.example.filrouge42.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormesController {

    @Autowired
    private FormesServiceImpl formesService;

    @GetMapping("")
    public List<FormeGeometrique> getAllForms() {
        return formesService.getAllForms();
    }

    @PostMapping
    public FormeGeometrique addForme(@RequestBody FormeGeometriqueDTO formeDTO) {
        FormeGeometrique formeGeometrique = convertToEntity(formeDTO);
        return formesService.addForme(formeGeometrique);
    }

    private FormeGeometrique convertToEntity(FormeGeometriqueDTO formeDTO) {
        return switch (formeDTO.getTypeForme()) {
            case "RECTANGLE" -> new Rectangle(
                formeDTO.getLongueur(),
                formeDTO.getLargeur(),
                formeDTO.getRotation(),
                formeDTO.getPosition()
            );
            case "TRIANGLE" -> new Triangle(formeDTO.getCote());
            case "CARRE" -> new Carre(formeDTO.getCote());
            case "CERCLE" -> new Cercle(formeDTO.getRayon());
            default -> throw new IllegalArgumentException("Type de forme non reconnu : " + formeDTO.getTypeForme());
        };
    }

    @GetMapping("/{index}")
    public FormeGeometrique getFormeByIndex(@PathVariable int index) {
        return formesService.getFormeByIndex(index);
    }

    // @PutMapping("/{index}")
    // public FormeGeometrique updateForme(@PathVariable int index, @RequestBody FormeGeometriqueDTO formeDTO) {
    //     FormeGeometrique formeGeometrique = convertToEntity(formeDTO);
    //     return formesService.updateForme(index, formeGeometrique);
    // }

    @DeleteMapping("/{index}")
    public void deleteForme(@PathVariable Long id) {
        formesService.deleteForme(id);
    }
}
