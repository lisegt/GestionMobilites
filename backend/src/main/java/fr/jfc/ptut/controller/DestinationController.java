package fr.jfc.ptut.controller;

import java.util.Set;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.jfc.ptut.entity.Destination;
import fr.jfc.ptut.entity.Type;
import fr.jfc.ptut.repository.DestinationRepository;

@RestController
@RequestMapping(path = "/api")
public class DestinationController {
    
    @Autowired
    private DestinationRepository destinationRepository;

    @GetMapping(value="/destinations/pays")
    //fonction qui renvoie une liste contenant tous les pays des destinations existantes
    public Set<String> findAllPays(){
        //On initialise la liste de pays qu'on renvoie, c'est une Set car on ne veut pas de doublons
        Set<String> pays = new HashSet<>();

        //On recupère toutes les destinations
        List<Destination> destinations = destinationRepository.findAll();

        //on parcourt chaque destination et on récupère son pays dans la Set
        for (Destination d : destinations){
            pays.add(d.getPays());
        }
        return pays;
    }

    @GetMapping(value="/destinations/typesMobilite")
    //fonction qui renvoie une liste contenant tous les pays des destinations existantes
    public Set<Type> findAllTypesMobilite(){
        //On initialise la liste de types de mobilité qu'on renvoie, c'est une Set car on ne veut pas de doublons
        Set<Type> typesMobilite = new HashSet<>();

        //On recupère toutes les destinations
        List<Destination> destinations = destinationRepository.findAll();

        //on parcourt chaque destination et on récupère son type de mobilité dans la Set
        for (Destination d : destinations){
            typesMobilite.add(d.getTypeMobilite());
        }
        return typesMobilite;
    }

    @GetMapping(value="/destinations/statutsContrat")
    //fonction qui renvoie une liste contenant tous les pays des destinations existantes
    public Set<String> findAllStatutsContrat(){

        //On initialise la liste des statuts de contrat qu'on renvoie, c'est une Set car on ne veut pas de doublons
        Set<String> statutsContrat = new HashSet<>();

        //On recupère toutes les destinations
        List<Destination> destinations = destinationRepository.findAll();

        //on parcourt chaque destination et on récupère la date de fin de contrat dans la Set
        for (Destination d : destinations){
            long differenceEntreDates = ChronoUnit.DAYS.between(LocalDate.now(), d.getDateFinDeContratIsis());
            System.out.println(differenceEntreDates);

            if (differenceEntreDates > 365){
                statutsContrat.add("Valide");
            } else if (differenceEntreDates > 0){
                statutsContrat.add("Bientôt Expiré");
            } else {
                statutsContrat.add("Expiré");
            }
        }
        return statutsContrat;
    }
}
