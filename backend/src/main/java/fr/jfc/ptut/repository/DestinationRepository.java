package fr.jfc.ptut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.DestinationEntity;

public interface DestinationRepository extends JpaRepository<DestinationEntity, Integer> {
    
}
