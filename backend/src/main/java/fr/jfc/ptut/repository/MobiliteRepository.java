package fr.jfc.ptut.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.jfc.ptut.entity.Mobilite;

public interface MobiliteRepository extends JpaRepository<Mobilite, Integer> {
<<<<<<< HEAD
    @Query(
        value="SELECT * "
             +"FROM mobilite "
             +"WHERE etudiant_id = :id",
        nativeQuery = true
    )
    List<Mobilite> mobiliteByIdEtudiant(int id);

    
=======
   
>>>>>>> ec520197b4ae0ec7e9ab01995e929c052ad6734c
}
