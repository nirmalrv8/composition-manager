package com.example.filrouge42.Services;

import com.example.filrouge42.Repository.FormeGeometriqueRepository;
import com.example.filrouge42.model.FormeGeometrique;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FormesServiceImpl implements FormesService {

    private final FormeGeometriqueRepository formeRepository;

    public FormesServiceImpl(FormeGeometriqueRepository formeRepository) {
        this.formeRepository = formeRepository;
    }
//    private List<FormeGeometrique> formes = new ArrayList<>();

    @Override
    public List<FormeGeometrique> getAllForms() {
        return formeRepository.findAll();
    }

    @Override
    public FormeGeometrique addForme(FormeGeometrique forme) {
        return formeRepository.save(forme);
    }

    @Override
    public FormeGeometrique getFormeByIndex(int index) {
//        if (index >= 0 && index < formes.size()) {
//            return formes.get(index);
//        } else {
        // Cette méthode n'est plus nécessaire car je n'utilisez plus la liste `formes`

        return null;
    }

    // @Override
    // public FormeGeometrique updateForme(int index, FormeGeometrique forme) {
    //     if (formeRepository.existsById((long) index)) {
    //         forme.setId((long) index); // Assurez-vous que l'ID est défini pour mettre à jour la bonne entrée
    //         return formeRepository.save(forme);
    //     } else {
    //         return null;
    //     }
    // }

    @Override
    public void deleteForme(Long id) {
        if (formeRepository.existsById(id)) {
            formeRepository.deleteById(id);
        }
    }
}