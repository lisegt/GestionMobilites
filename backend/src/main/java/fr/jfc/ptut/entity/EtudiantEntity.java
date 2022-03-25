package fr.jfc.ptut.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class EtudiantEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private int numEtud;
    
    @NonNull
    private String nom;

    @NonNull
    private String prenom;

    @NonNull
    private String promo;

    @OneToMany(mappedBy="etudiant")
    @ToString.Exclude // On ne veut pas inclure la liste des mob dans le toString
    // Sinon récursivité infinie 
    private List<MobiliteEntity> mobilites = new LinkedList<>();
}