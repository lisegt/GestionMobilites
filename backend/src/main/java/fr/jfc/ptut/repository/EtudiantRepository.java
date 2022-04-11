package fr.jfc.ptut.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.jfc.ptut.entity.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    
    @Query(
        value="SELECT promo FROM etudiant",
        nativeQuery = true
    )
    List<String> promo();

}
