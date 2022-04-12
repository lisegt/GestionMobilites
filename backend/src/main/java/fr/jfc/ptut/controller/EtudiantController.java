package fr.jfc.ptut.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.jfc.ptut.entity.EtatMobilite;
import fr.jfc.ptut.entity.Etudiant;
import fr.jfc.ptut.entity.Mobilite;
import fr.jfc.ptut.entity.Type;
import fr.jfc.ptut.repository.EtudiantRepository;

@RestController
@RequestMapping(value="/api")
public class EtudiantController {

    @Autowired
    EtudiantRepository etudiantRepository;
    
    @GetMapping("/etudiant/promo")
    public Set<Integer> getPromo(){
        Set<Integer> res = new HashSet<>();
        //On charge tout les étudiants
        List<Etudiant> etudiants = etudiantRepository.findAll();
        //On parcourt les étudiants
        for (Etudiant etud: etudiants){
            res.add(etud.getPromo());
        }
        return res;
    }

    @GetMapping("/findEtudiant/{promo}")
    public Set<Etudiant> getEtudiantByPromo(@PathVariable String promo){
        Integer promoEtud = Integer.valueOf(promo);
        Set<Etudiant> res = new HashSet<>();
        List<Etudiant> etudiants = etudiantRepository.findAll();
        for(Etudiant etud : etudiants){
            if(etud.getPromo().equals(promoEtud)){
                res.add(etud);
            }
        }
        return res;
    }

    @GetMapping("/etudiantInfoMobilite")
    public Set<EtudiantMobilite> getEtudiantAndMobiliteInfos(){
        Set<EtudiantMobilite> res = new HashSet<>();
        //On récupère les étudiants
        List<Etudiant> etudiants = etudiantRepository.findAll();
        for(Etudiant etud : etudiants){
            EtudiantMobilite etudMob = new EtudiantMobilite();
            //On set les attributs de base
            etudMob.setNom(etud.getNom());
            etudMob.setPrenom(etud.getPrenom());
            etudMob.setIne(etud.getNumEtud());
            etudMob.setPromotion(etud.getPromo());
            //On set les attributs que on doit calculer
            //EtatMobilité
            List<Mobilite> mobilitesEtud = etud.getMobilites();
            //On regarde si l'etudiant à valide sa mobilité
            //Si l'etudiant à des mobilitées
            if(! mobilitesEtud.isEmpty() ){
                etudMob.setEtatMobilite(EtatMobilite.VALIDEE );
                etudMob.setTypeMobilite(etud.getMobilites().get(0).getDestination().getTypeMobilite());
            }
            //Si l'etudiant n'a pas de mobilité
            else{
                etudMob.setEtatMobilite(EtatMobilite.NON_VALIDEE);
                etudMob.setTypeMobilite(Type.PAS_DE_MOBILITE);
            }
            res.add(etudMob);
        }
        return res;
    }

    public static class EtudiantMobilite {

        Integer ine;
        String nom;
        String prenom;
        Integer promotion;
        EtatMobilite etatMobilite;
        Type typeMobilite;

        public EtudiantMobilite() {}

        public Integer getIne() {
            return ine;
        }

        public void setIne(Integer ine) {
            this.ine = ine;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public Integer getPromotion() {
            return promotion;
        }

        public void setPromotion(Integer promotion) {
            this.promotion = promotion;
        }

        public EtatMobilite getEtatMobilite() {
            return etatMobilite;
        }

        public void setEtatMobilite(EtatMobilite etatMobilite) {
            this.etatMobilite = etatMobilite;
        }

        public Type getTypeMobilite() {
            return typeMobilite;
        }

        public void setTypeMobilite(Type typeMobilite) {
            this.typeMobilite = typeMobilite;
        }

        
    }

}
