package fr.jfc.ptut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    
}
