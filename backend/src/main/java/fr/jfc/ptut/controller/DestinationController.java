package fr.jfc.ptut.controller;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.jfc.ptut.entity.Destination;
import fr.jfc.ptut.repository.DestinationRepository;

@RestController
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
}
