package fr.jfc.ptut.entity;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class MobiliteEntity {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @NonNull
    private LocalDate dateDepart;
    
    private String retourExperience;

    @NonNull
    private int dureeEnMois;

    private Periode periode;

    @NonNull
    @ManyToOne(optional = false) //obligatoire car clé étrangère non nulle
    private EtudiantEntity etudiant;
}
