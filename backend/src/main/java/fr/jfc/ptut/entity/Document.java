package fr.jfc.ptut.entity;

import java.io.File;

import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Document {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @NonNull
    private File fichier;

    @NonNull
    private String intitule;

    private String description;
}
