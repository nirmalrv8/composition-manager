package com.example.filrouge42.Services;

import com.example.filrouge42.model.Composition;
import com.example.filrouge42.model.FormeGeometrique;

import java.util.List;

public interface CompositionsService {
    List<Composition> getAllCompositions();
    Composition createComposition(Composition composition);
    void addFormeToComposition(long compositionId, FormeGeometrique forme);
    <Forme> List<Forme> getFormsInComposition(long compositionId);
    Composition getCompositionbyID(Long id);
    void deleteCompositionById(Long id);
}
