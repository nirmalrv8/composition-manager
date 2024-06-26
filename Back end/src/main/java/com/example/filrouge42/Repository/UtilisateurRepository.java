package com.example.filrouge42.Repository;

import com.example.filrouge42.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
