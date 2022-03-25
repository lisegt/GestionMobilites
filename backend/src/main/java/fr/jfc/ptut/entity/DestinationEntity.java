package fr.jfc.ptut.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    private Periode periode;

    private int nbPlaceSemestre;

    private int nbPlaceAnnee;

    private LocalDate dateFinDeContratIsis;

    @NonNull
    private String nomEtablissementAccueil;

    private String image;

    @ManyToOne
    private LocalisationEntity localisation;

}
