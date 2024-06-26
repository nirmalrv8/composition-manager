package com.example.filrouge42.Repository;

import com.example.filrouge42.model.Composition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompositionRepository extends JpaRepository<Composition, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
