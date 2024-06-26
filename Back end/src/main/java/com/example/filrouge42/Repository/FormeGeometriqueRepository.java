package com.example.filrouge42.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.filrouge42.model.FormeGeometrique;

@Repository
public interface FormeGeometriqueRepository extends JpaRepository<FormeGeometrique, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
