package fr.jfc.ptut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.DocumentEntity;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {
    
}
