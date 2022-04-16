package fr.jfc.ptut.controller;

import java.util.Set;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.jfc.ptut.entity.EtatMobilite;
import fr.jfc.ptut.entity.Etudiant;
import fr.jfc.ptut.entity.Mobilite;
import fr.jfc.ptut.entity.Type;
import fr.jfc.ptut.repository.MobiliteRepository;

@RestController
@RequestMapping(path = "/api")
public class MobiliteController {
    
    @Autowired
    private MobiliteRepository mobiliteRepository;

    @GetMapping(value="/mobilites/promotions")
    //fonction qui renvoie une liste contenant toutes les promotions des étudiants ayant effectué une mobilité
    public Set<Integer> findAllPromo(){

        //On initialise une liste qui va contenir tous les étudiants
        List<Etudiant> etudiants = new ArrayList<>();

        //On initialise la liste de promotions qu'on renvoie, c'est une Set car on ne veut pas de doublons
        Set<Integer> promotions = new HashSet<>();

        //On recupère toutes les destinations
        List<Mobilite> mobilites = mobiliteRepository.findAll();

        //on parcourt chaque mobilité et on récupère les étudiants dans une liste
        for (Mobilite m : mobilites){
            etudiants.add(m.getEtudiant());
        }

        //on parcourt chaque étudiants et on récupère les différentes promotions dans une Set
        for (Etudiant e : etudiants){
            promotions.add(e.getPromo());
        }

        return promotions;
    }

    @GetMapping(value="/mobilites/destinations")
    //fonction qui renvoie une liste contenant toutes les destinations des mobilités effectuées
    public Set<String> findAllDestinations(){

        //On initialise la liste des destinations qu'on renvoie, c'est une Set car on ne veut pas de doublons
        Set<String> destinations = new HashSet<>();

        //On récupère toutes les destinations
        List<Mobilite> mobilites = mobiliteRepository.findAll();

        //on parcourt chaque mobilité et on récupère la destination (nom de lé'établissement + ville) dans la Set
        for (Mobilite m : mobilites){
            destinations.add(m.getDestination().getNomEtablissementAccueil() + " (" +m.getDestination().getVille()+ ")");
        }
        return destinations;
    }

    
    @GetMapping(value="/destinations/etatsMobilite")
    //fonction qui renvoie une liste contenant tous les états de mobilité des mobilités existantes
    public Set<String> findAllEtatsMobilite(){

        //On initialise la liste des statuts de contrat qu'on renvoie, c'est une Set car on ne veut pas de doublons
        Set<String> etatsMobilite = new HashSet<>();

        //On initialise une liste qui va contenir tous les états de mobilité
        List<EtatMobilite> etats = new ArrayList<>();

        //On recupère toutes les mobilités
        List<Mobilite> mobilites = mobiliteRepository.findAll();

        //on parcourt chaque mobilité et on récupère l'état de la mobilité via la méthode etatMobilite()
        for (Mobilite m : mobilites){
            etats.add(m.etatMobilite());
        }

        //on parcourt chaque état de mobilité et en fonction de sa valeur, on remplit la Set de String prédéfinis
        for (EtatMobilite e : etats){
            if (e.equals("NON_VALIDEE")){
                etatsMobilite.add("Non Validée");
            } else if (e.equals("EN_COURS")){
                etatsMobilite.add("À Venir");
            } else {
                etatsMobilite.add("Validée");
            }
        }
        
        return etatsMobilite;
    }
    /*
    @GetMapping(value="/destinations/findByStatutContrat")
    //fonction qui renvoie une liste contenant tous les pays des destinations existantes
    public List<Destination> findByStatutContrat(String statut){

        String statutContrat;
        //On initialise la liste des destinations qu'on renvoie
        List<Destination> listeDestinations = new ArrayList<>();

        //On recupère toutes les destinations
        List<Destination> destinations = destinationRepository.findAll();

        //on parcourt chaque destination et on l'ajoute à listeDestinationByStatutContrat si le statut de la mobilite correpons au statut sélectionné
        for (Destination destination : destinations){
            long differenceEntreDates = ChronoUnit.DAYS.between(LocalDate.now(), destination.getDateFinDeContratIsis());

            //on attribue un statut à une destination en fonction du nombre de jours restant
            if (differenceEntreDates > 365){
                statutContrat = "Valide";
            } else if (differenceEntreDates > 0){
                statutContrat = "Bientôt Expiré";
            } else {
                statutContrat = "Expiré";
            }

            //si le statut de la destination correspond au statut passé en paramètre, on l'ajoute à la liste à afficher
            if (statutContrat.equals(statut)){
                listeDestinations.add(destination);
            }
        }
        return listeDestinations;
    }
    */
}
