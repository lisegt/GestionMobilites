package fr.jfc.ptut.entity;

import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class DocumentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @NonNull
    private String fichier;

    @NonNull
    private String intitule;

    private String description;
}
