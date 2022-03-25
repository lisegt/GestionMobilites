package fr.jfc.ptut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.LocalisationEntity;

public interface LocalisationRepository extends JpaRepository<LocalisationEntity, Integer> {
    
}
