package fr.jfc.ptut.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.jfc.ptut.entity.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    /*
    @Query(value= "SELECT pays "
        + "FROM destination ",
        nativeQuery = true)
    public List<PaysDestinationResult> pays();
    */
}
