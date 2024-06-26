package com.example.filrouge42.Services;

import com.example.filrouge42.Repository.CompositionRepository;
import com.example.filrouge42.model.Composition;
import com.example.filrouge42.model.FormeGeometrique;
import com.example.filrouge42.model.Rectangle;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompositionsServiceImpl implements CompositionsService {

    private final CompositionRepository compositions;
    private long nextId = 1;

    public CompositionsServiceImpl(CompositionRepository compositions) {
        this.compositions = compositions;
    }

    @Override
    public List<Composition> getAllCompositions() {
        List<Composition> compositionList = compositions.findAll();
        for (Composition composition : compositionList) {
            composition.setAire(composition.calculerAireTotale());
            composition.setPerimetre(composition.calculerPerimetreTotal());
        }
        return compositionList;
    }

    @Override
    public Composition getCompositionbyID(Long id) {
        Composition composition = compositions.findById(id).orElse(null);
        return composition;
    }

    @Override
    public Composition createComposition(Composition composition) {
        composition = this.compositions.save(composition);
        return composition;
    }

    @Override
    public void addFormeToComposition(long compositionId, FormeGeometrique forme) {
        Composition composition = compositions.findById(compositionId).orElse(null);
        if (composition != null) {
            composition.ajouterForme(forme);
            this.compositions.save(composition);
        }
    }

    @Override
    public List<FormeGeometrique> getFormsInComposition(long compositionId) {
        Composition composition = compositions.findById(compositionId).orElse(null);
        if (composition != null) {
            return composition.getFormes();
        } else {
            return null;
        }
    }

    @Override
    public void deleteCompositionById(Long id) {
        compositions.deleteById(id);
    }
}
