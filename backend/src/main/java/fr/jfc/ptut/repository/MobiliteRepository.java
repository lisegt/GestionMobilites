package fr.jfc.ptut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.Mobilite;

public interface MobiliteRepository extends JpaRepository<Mobilite, Integer> {
    
}