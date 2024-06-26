package com.example.filrouge42.Repository;

import com.example.filrouge42.model.Partage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartageRepository extends JpaRepository<Partage, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
