package fr.jfc.ptut.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.jfc.ptut.entity.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    
    

}
