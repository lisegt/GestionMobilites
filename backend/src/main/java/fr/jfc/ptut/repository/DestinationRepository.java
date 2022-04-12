package fr.jfc.ptut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import antlr.collections.List;
import fr.jfc.ptut.entity.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    @Query(value="SELECT * "
    +"FROM destination "
    +"WHERE destination_id = :id",
        nativeQuery = true)
    Destination destinationById(int id);

}
