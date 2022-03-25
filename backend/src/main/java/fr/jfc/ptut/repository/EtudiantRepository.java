package fr.jfc.ptut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    
}
