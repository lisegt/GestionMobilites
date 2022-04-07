package fr.jfc.ptut.entity;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Mobilite {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @NonNull
    private LocalDate dateDepart;
    
    private String retourExperience;

    private int dureeEnMois;

    @NonNull
    private Periode periode;

    @NonNull
    @ManyToOne(optional = false) //obligatoire car clé étrangère non nulle
    private Etudiant etudiant;

    @NonNull
    @ManyToOne(optional = false)
    private Destination destination;

   

    
}
