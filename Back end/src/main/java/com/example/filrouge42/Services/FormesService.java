// FormesService.java
package com.example.filrouge42.Services;

import com.example.filrouge42.model.FormeGeometrique;
import java.util.List;

public interface FormesService {
    List<FormeGeometrique> getAllForms();
    FormeGeometrique addForme(FormeGeometrique forme);
    FormeGeometrique getFormeByIndex(int index);
    // FormeGeometrique updateForme(int index, FormeGeometrique forme);
    void deleteForme(Long id);
}
