package fr.jfc.ptut.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

<<<<<<< HEAD
import antlr.collections.List;
import fr.jfc.ptut.entity.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    @Query(value="SELECT * "
    +"FROM destination "
    +"WHERE destination_id = :id",
        nativeQuery = true)
    Destination destinationById(int id);

=======
import fr.jfc.ptut.entity.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    /*
    @Query(value= "SELECT pays "
        + "FROM destination ",
        nativeQuery = true)
    public List<PaysDestinationResult> pays();
    */
>>>>>>> ec520197b4ae0ec7e9ab01995e929c052ad6734c
}
