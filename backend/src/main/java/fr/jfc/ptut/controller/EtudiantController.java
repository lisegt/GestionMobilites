package fr.jfc.ptut.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.jfc.ptut.entity.EtatMobilite;
import fr.jfc.ptut.entity.Etudiant;
import fr.jfc.ptut.entity.Mobilite;
import fr.jfc.ptut.repository.EtudiantRepository;
import fr.jfc.ptut.repository.MobiliteRepository;

@RestController
public class EtudiantController {
    
    @Autowired
    MobiliteRepository mobiliteRepository;

    @Autowired
    EtudiantRepository etudiantRepository;
    
    @GetMapping(value="/etudiant/mobilite/{index}")
    public ResponseEntity findEtudiantByEtatMobilite(@PathVariable("index") String index){
        //On initialise la liste que on renvoie
        Set<Etudiant> res = new HashSet<>();
        //On recupère tous les étudiants
        List<Etudiant> etudiants = etudiantRepository.findAll();

        //Si index = 1 on renvoie etudiant avec mobilitee non valide
        if(Integer.valueOf(index) == 1){
            /*
            for (Etudiant etud : etudiants){
                //On récupère les mobilités de l'étudiant
                List<Mobilite> mobilitees = etud.getMobilites();

                //Si pas de mobilité
                if(mobilitees.isEmpty()){
                    res.add(etud);
                }
                //Si il y a des mobilités
                else{
                    //On regarde si elles sont non valides
                    for(Mobilite m : mobilitees){
                        if(m.etatMobilite() == EtatMobilite.NON_VALIDEE){
                            res.add(etud);
                        }
                    }
                }
            }
            */
            return new ResponseEntity(mobiliteRepository.mobiliteByIdEtudiant(3)  ,HttpStatus.ACCEPTED);
        }
        //Si index = 2 on renvoie etudiant avec mobilite valide
        if(Integer.valueOf(index) == 2){
            return new ResponseEntity("valide",HttpStatus.ACCEPTED);
        }
        //Si index = 3 on renvoie étudiant avec mobilité en cours
        if(Integer.valueOf(index) == 3){
            return new ResponseEntity("en cours",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
