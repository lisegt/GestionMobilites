package fr.jfc.ptut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.MobiliteEntity;

public interface MobiliteRepository extends JpaRepository<MobiliteEntity, Integer> {
    
}
