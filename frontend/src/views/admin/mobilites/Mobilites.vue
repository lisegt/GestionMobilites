<template>
  <div class="h-100 container ">
  <div class="row h-25  align-items-center">

    <div class="col-4 h-50  d-flex flex-column justify-content-around align-items-left">
      <input type="text" placeholder="RECHERCHER UN ETUDIANT..." class="w-100 inputFiltre">
      <div class="filtreTab w-100 d-flex  ">
        <div class="dropdown">
          <button aria-expanded="false" type="button" id="dropdownMenuButton" data-toggle="dropdown" class="btn flex-fill dropdown-toggle "> PROMOTION</button>
          <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
          <li ><a class="dropdown-item btn" type="button" href="#">Action</a></li>
          
        </ul>
        </div>
          <a type="button" class="btn flex-fill"> DESTINATION</a>
          <a type="button" class="btn flex-fill ">ETAT MOBILITE</a>
      </div>
    </div>

    <h1 class="col-4 text-center">GESTION DES MOBILITES</h1>

    <div class="col-4 text-right">
        <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#exempleModalM">
            <img img v-bind:src="globe" alt="globe" class="mr-1">
            Ajouter une mobilité
        </button>
    </div>

  </div>
  

  <TableMobilites @set="setMobilite" @delete="deleteMobilite" v-bind:mobilites="listeMobilitesTab"/>
  <FormAddMobilites @ajouter="addMobilite" />
  </div>
  
</template>

<script setup>

    import {reactive, onMounted} from 'vue'
    import TableMobilites from './tableMobilites/TableMobilites.vue'
    import FormAddMobilites from './formAddMobilite/FormAddMobilite.vue';
    import globe from '../../../img/globe.png'

    //navabr active
    let listeNav = ["accueilNav","etudiantNav","destinationsNav","mobilitesNav","docNav","siteNav"]
    for(let l of listeNav){
    document.getElementById(l).classList.remove("active")
    }
    document.getElementById("mobilitesNav").classList.add("active")

    const listeMobilites =  reactive([])
    const listeMobilitesTab = reactive([])


   function dateDiff(date1, duree){
                var diff = {}  
                
                let dateRetour = new Date(date1)
                console.log("date depart",duree)         
                          // Initialisation du retour
                console.log(dateRetour.setMonth(dateRetour.getMonth()+ 5))
                console.log("date retour",dateRetour) 
                let actuelDate= new Date()
                
                var tmp = dateRetour - actuelDate;
            
                tmp = Math.floor(tmp/1000);             // Nombre de secondes entre les 2 dates
                diff.sec = tmp % 60;                    // Extraction du nombre de secondes
            
                tmp = Math.floor((tmp-diff.sec)/60);    // Nombre de minutes (partie entière)
                diff.min = tmp % 60;                    // Extraction du nombre de minutes
            
                tmp = Math.floor((tmp-diff.min)/60);    // Nombre d'heures (entières)
                diff.hour = tmp % 24;                   // Extraction du nombre d'heures
                
                tmp = Math.floor((tmp-diff.hour)/24);   // Nombre de jours restants
                diff.day = tmp;
                console.log(diff)
                return diff.day;
    }

    function getMobilites(){
        
        listeMobilites.splice(0,listeMobilites.length)         //On vide la liste des destinations avant de la remplir afin d'éviter les doublons
        listeMobilitesTab.splice(0,listeMobilitesTab.length) 

        let url = `http://localhost:8989/api/mobilites`
        fetch(url)
        .then((res)=>{return res.json()})
        .then((json)=>{
       
           
            let dateActuel = new Date()
            
           
            for(let d of json._embedded.mobilites){
                  console.log(d)
                  
                  fetch(d._links.etudiant.href)
                  .then((res)=>{return res.json()})
                  .then((json)=>{
                    return json
                  })
                  .then((etudiant)=>{
                    console.log(etudiant)
                    fetch(d._links.destination.href)
                    .then((res)=>{return res.json()})
                    .then((destination)=>{
                        return destination
                      })
                    .then((destination)=>{
                    
                    if(dateActuel.getTime()<new Date(d.dateDepart).getTime()){
                      
                      listeMobilites.push([d,etudiant,destination,"Pas commencée"])
                      listeMobilitesTab.push([d,etudiant,destination,"Pas commencée"])
                    }
                    else{
                      if(dateDiff(d.dateDepart,d.dureeEnMois)<0){
                          
                          listeMobilites.push([d,etudiant,destination,"Terminée"])
                          listeMobilitesTab.push([d,etudiant,destination,"Terminée"])
                      }
                      else{
                        
                        listeMobilites.push([d,etudiant,destination,"En cours"])
                        listeMobilitesTab.push([d,etudiant,destination,"En cours"])
                      }}
                   
                    
                    })

                  })
                  
                    
                    
                 
                   
                    

            }
            
        })
    }

    function deleteMobilite(id){
        console.log('delete',id)
        let myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        const fetchOptions = {
                method: "DELETE",
                headers: myHeaders
                };
        let url = `http://localhost:8989/api/mobilites/${id}`
        fetch(url,fetchOptions)
        .then(()=>{getMobilites()})
        .catch((err)=>{
            window.alert("Vous ne pouvez pas supprimer une destination qui est liée à une ou plusieurs mobilités")
            console.log("message d'erreur : ",err)})
    }
    

    function setMobilite(mobilite){
        document.getElementById("nomEtablissement").value=destination[0].nomEtablissementAccueil
        document.getElementById("nomVille").value=destination[0].ville
        document.getElementById("nomPays").value=destination[0].pays
        document.getElementById(`${destination[0].typeMobilite}`).selected = true
        document.getElementById("semestres").value= parseInt(destination[0].nbPlaceSemestre)
        document.getElementById("nbPlaceAnnee").value=parseInt(destination[0].nbPlaceAnnee)
        document.getElementById("dateFinContrat").value=destination[0].dateFinDeContratIsis

        document.getElementById("btnSub").addEventListener('click',()=>{
            console.log(destination)
            updateMobilite(destination)
        })
    }

    function updateMobilite(mobilite){

           let nomEtablissement = document.getElementById("nomEtablissement").value
           let nomVille = document.getElementById("nomVille").value
           let nomPays = document.getElementById("nomPays").value
           let typeMobilite = document.getElementById("typeMobilite").value
           let nbPlaceSemestre = document.getElementById("semestres").value
           let nbPlaceAnnee= document.getElementById("nbPlaceAnnee").value
           let date = document.getElementById("dateFinContrat").value

            const url = `http://localhost:8989/api/destinations/${destination[0].id}` // l’url de l'API

            let myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");
            const fetchOptions = {  method:"PUT", 
                                    headers: myHeaders, 
                                    body: JSON.stringify({
                                        nomEtablissementAccueil:nomEtablissement,
                                        dateFinDeContratIsis:date,
                                        nbPlaceAnnee:nbPlaceAnnee,
                                        nbPlaceSemestre:nbPlaceSemestre,
                                        typeMobilite:typeMobilite,
                                        ville:nomVille,
                                        pays:nomPays
                                        })};
            fetch(url,fetchOptions)
            .catch((error) => console.log(error));

    }
  
    function addMobilite() {
    
    
    const url = `http://localhost:8989/api/` // l’url de l'API
    let idDestination = document.getElementById("addDestination").value
    let idEtudiant = document.getElementById("selectEtud").value
    alert(idDestination)
    alert(idEtudiant)
    let date = document.getElementById("addDepart").value
    let duree = document.getElementById("addDuree").value
    let periode = document.getElementById("periode").value
    
      
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const fetchOptions = {  method:"POST", 
                            headers: myHeaders, 
                            body: JSON.stringify({
                                dateDepart:date,
                                dureeEnMois:duree,
                                periode:periode,
                                destination:`http://localhost:8989/api/destinations/${idDestination}`,
                                etudiant:`http://localhost:8989/api/etudiants/${idEtudiant}`,
                                retourExperience:""

                                })};
    console.log("fetchOptions : ",fetchOptions.body)
    fetch("http://localhost:8989/api/mobilites",fetchOptions)
    .then(()=>{getMobilites()})
    .catch((error) =>{ 
      console.log(error)
      window.alert(date)});


  



    
    


    
    
}

    onMounted(()=>{
        getMobilites()
        
    })
</script>

<style>
</style>