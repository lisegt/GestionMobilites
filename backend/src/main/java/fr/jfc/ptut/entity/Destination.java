package fr.jfc.ptut.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
public class DestinationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private Type typeMobilite;

    private int nbPlaceSemestre;

    private int nbPlaceAnnee;

    private LocalDate dateFinDeContratIsis;

    @NonNull
    private String nomEtablissementAccueil;

    private String image;

    @NonNull
    @ManyToOne
    private LocalisationEntity localisation;

    @OneToMany(mappedBy = "destination")
    @ToString.Exclude
    private List<MobiliteEntity> mobilites = new ArrayList<>();

}
