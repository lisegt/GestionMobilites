package fr.jfc.ptut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.EtudiantEntity;

public interface EtudiantRepository extends JpaRepository<EtudiantEntity, Integer> {
    
}
