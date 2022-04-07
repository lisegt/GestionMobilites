package fr.jfc.ptut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EtudiantController {
    
    
    @GetMapping(value="/etudiant/mobilite/{index}")
    public ResponseEntity findEtudiantByEtatMobilite(@PathVariable("index") String index){
        //Si index = 1 on renvoie etudiant avec mobilitee non valide
        if(Integer.valueOf(index) == 1){
            return new ResponseEntity("non valide",HttpStatus.ACCEPTED);
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
