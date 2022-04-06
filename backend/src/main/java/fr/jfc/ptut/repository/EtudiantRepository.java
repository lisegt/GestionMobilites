package fr.jfc.ptut.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    Optional<Etudiant> findByNom(String nom);
}
