<template>
  <div class=" mt-5 container ">
  <div class="row h-25  align-items-center">

    <div class="col-4 h-50  d-flex flex-column justify-content-around align-items-left">
    <SearchMobilite v-bind:mobilites="listeMobilitesTab" @searchMobilite="searchMobilite"/>
    <FiltreMobilites @searchByPromo="searchByPromo" @searchByDestination="searchByDestination" @searchByEtatMobilite="searchByEtatMobilite" />
    </div>

    <h1 class="col-4 text-center">GESTION DES MOBILITES</h1>

    <div class="col-4 text-right">
        <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#exempleModalM">
            <img img v-bind:src="globe" alt="globe" class="mr-1">
            Ajouter une mobilité
        </button>
    </div>

  <TableMobilites @addDoc="addDoc" @delete="deleteMobilite" v-bind:mobilites="listeMobilitesTab" class="mt-4"/>
  
  </div>
  
  <FormAddMobilites @ajouter="addMobilite" />
  <FormAddDoc v-bind:mobilite="mobilite" @updateMobilite="updateMobilite" @updateFile="setFile"/>
  </div>
  
</template>

<script setup>

    import {reactive, onMounted, ref} from 'vue'

    import TableMobilites from './tableMobilites/TableMobilites.vue'
    import FormAddMobilites from './formAddMobilite/FormAddMobilite.vue';
    import FormAddDoc from './formAddDoc/formAddDoc.vue'
    import SearchMobilite from './searchMobilite/SearchMobilite.vue'
    import FiltreMobilites from './filtreMobilites/FiltreMobilites.vue'
    import globe from '../../../img/globe.png'

    /**
     * Activer les items de la navbar selon la page consultée
     * On retire l'attribut de tous les items de la nav et on le rajoute à l'item de la page concernée
    **/
    let listeNav = ["accueilNav","etudiantNav","destinationsNav","mobilitesNav","docNav","siteNav"]
    for(let l of listeNav){
    document.getElementById(l).classList.remove("active")
    }
    document.getElementById("mobilitesNav").classList.add("active")

    const listeMobilites =  reactive([])
    const listeMobilitesTab = reactive([])
    let mobilite = ref({})
    let file =ref("")

   function dateDiff(date1, duree){
                var diff = {}  
                
                let dateRetour = new Date(date1) // Initialisation du retour
               
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
              
                return diff.day;
    }
  const urlAllMobilites = `http://localhost:8989/api/mobilites`

  function getMobilites(url){
        
        listeMobilites.splice(0,listeMobilites.length)         //On vide la liste des destinations avant de la remplir afin d'éviter les doublons
        listeMobilitesTab.splice(0,listeMobilitesTab.length) 

        
        fetch(url, {method:'GET',headers: {"Authorization": localStorage.getItem('jwt')}})
        .then((res)=>{return res.json()})
        .then((json)=>{
       
           
            let dateActuel = new Date()
            
           
            for(let d of json._embedded.mobilites){
              
                  
                  fetch(d._links.etudiant.href, {method:'GET',headers: {"Authorization": localStorage.getItem('jwt')}})
                  .then((res)=>{return res.json()})
                  .then((json)=>{
                    return json
                  })
                  .then((etudiant)=>{
                    
                    fetch(d._links.destination.href, {method:'GET',headers: {"Authorization": localStorage.getItem('jwt')}})
                    .then((res)=>{return res.json()})
                    .then((destination)=>{
                        return destination
                      })
                    .then((destination)=>{
                    
                    if(dateActuel.getTime()<new Date(d.dateDepart).getTime()){
                      
                      listeMobilites.push([d,etudiant,destination,"Non Validée"])
                      listeMobilitesTab.push([d,etudiant,destination,"Non Validée"])
                    }
                    else{
                      if(dateDiff(d.dateDepart,d.dureeEnMois)<0){
                          
                          listeMobilites.push([d,etudiant,destination,"Validée"])
                          listeMobilitesTab.push([d,etudiant,destination,"Validée"])
                      }
                      else{
                        
                        listeMobilites.push([d,etudiant,destination,"En cours"])
                        listeMobilitesTab.push([d,etudiant,destination,"En cours"])
                      }}
                    })
                    console.log(listeMobilitesTab)
                  })  
            }
        })
  }

  function deleteMobilite(id){
      
      

      const fetchOptions = {
              method: "DELETE",
              headers: {"Authorization": localStorage.getItem('jwt'), "Content-Type":"application/json"}
              };
      let url = `http://localhost:8989/api/mobilites/${id}`
      fetch(url,fetchOptions)
      .then(()=>{getMobilites(urlAllMobilites)})
      .catch((err)=>{
          window.alert("Vous ne pouvez pas supprimer une destination qui est liée à une ou plusieurs mobilités")
          console.log("message d'erreur : ",err)})
  }
    
  function setFile(event){
  
  
  let reader = new FileReader();
  reader.onloadend = function() {
        
        file.value=reader.result
        
      }
  reader.readAsDataURL(event.target.files[0]);

}

  function addDoc(mobi){
      
      mobilite.value=mobi
      console.log("mobilite select: ",mobilite.value)
      
  }

    function updateMobilite(event){

           event.preventDefault()

            const url = `http://localhost:8989/api/mobilites/${mobilite.value[0].id}` // l’url de l'API

            const fetchOptions = {  method:"PUT", 
                                    headers: {"Authorization": localStorage.getItem('jwt'), "Content-Type" : "application/json"},
                                    body: JSON.stringify({
                                        dateDepart:mobilite.value[0].dateDepart,
                                        retourExperience:file.value,
                                        dureeEnMois:mobilite.value[0].dureeEnMois,
                                        periode:mobilite.value[0].periode,
                                        destination:`http://localhost:8989/api/destinations/${mobilite.value[2].id}`,
                                        etudiant:`http://localhost:8989/api/etudiants/${mobilite.value[1].id}`

                                        
                                        })};
            fetch(url,fetchOptions)
            .then(()=>{
              getMobilites(urlAllMobilites)
            })
            .catch((error) => console.log(error));

    }
  
    function addMobilite() {
    
    
    const url = `http://localhost:8989/api/` // l’url de l'API
    let idDestination = document.getElementById("addDestination").value
    let idEtudiant = document.getElementById("selectEtud").value
  
    let date = document.getElementById("addDepart").value
    let duree = document.getElementById("addDuree").value
    let periode = document.getElementById("periode").value
    
      
    const fetchOptions = {  method:"POST", 
                            headers: {"Authorization": localStorage.getItem('jwt'), "Content-Type" : "application/json"},
                            body: JSON.stringify({
                                dateDepart:date,
                                dureeEnMois:duree,
                                periode:periode,
                                destination:`http://localhost:8989/api/destinations/${idDestination}`,
                                etudiant:`http://localhost:8989/api/etudiants/${idEtudiant}`,
                                retourExperience:""

                                })};
    fetch("http://localhost:8989/api/mobilites",fetchOptions)
    .then(()=>{getMobilites()})
    .catch((error) =>{ 
      console.log(error)
      window.alert(date)});
  }

function getMobilitesFiltrees(url){      
        listeMobilitesTab.splice(0,listeMobilitesTab.length) //On vide la liste des mobilités avant de la remplir afin d'éviter les doublons

        fetch(url, {meyhod: 'GET', headers: {"Authorization": localStorage.getItem('jwt')}})
        .then((response)=>{return response.json()})
        .then((dataJSON)=>{
           
            let dateActuel = new Date()

            dataJSON.forEach((mobilite)=>{ // on parcourt toutes les mobilités de la base de données
              
              let etud = mobilite.etudiant //on récupère l'étudiant associé à chaque mobilité
              let dest = mobilite.destination //on récupère la destination associé à chaque mobilité

              if(dateActuel.getTime()<new Date(mobilite.dateDepart).getTime()){ 
                //si la date actuelle se situe avant la date de départ, l'état de la mobilité est "Non Validée"
                listeMobilitesTab.push([mobilite,etud,dest,"Non Validée"])
              } else if (dateDiff(mobilite.dateDepart,mobilite.dureeEnMois)<0) { //si la mobilité est terminée
                listeMobilitesTab.push([mobilite,etud,dest,"Validée"])
              } else { //si la mobilité est en cours
                listeMobilitesTab.push([mobilite,etud,dest,"En Cours"])
              }
            })
        })
  }

/**
 * @param promo sélectionné dans la liste déroulante
 * fonction qui permet de récupérer toutes les mobilités concernant les étudiants de la promotion sélectionnée
 */
function searchByPromo(promo){
  const url = `/api/mobilites/findByPromo?promo=${promo}` //url permettant d'accéder aux mobilités filtrées par promo

  if(promo != 'tous'){ //si on sélectionne n'importe quelle promo de la liste déroulante, on filtre

    getMobilitesFiltrees(url) //on récupère les mobilités filtrées

  } else { // on sélectionne l'option permettant d'afficher toutes les mobilités

    getMobilites(urlAllMobilites)
  }
}

/**
 * @param destination sélectionné dans la liste déroulante
 * fonction qui permet de récupérer toutes les mobilités associées à la destination sélectionnée
 */
function searchByDestination(idDestination){

  const url = `/api/mobilites/findByIdDestination?id=${idDestination}` //url permettant d'accéder aux mobilités filtrées par destination
  
  if(idDestination != '0'){ //si on sélectionne n'importe quelle destination de la liste déroulante, on filtre
    getMobilitesFiltrees(url) //on récupère les mobilités filtrées
  } else { // on sélectionne l'option permettant d'afficher toutes les mobilités
    getMobilites(urlAllMobilites)
  }
}

/**
 * @param etat sélectionné dans la liste déroulante
 * fonction qui permet de récupérer toutes les mobilités associées à un état de mobilité
 */
function searchByEtatMobilite(etat){
  
  const url = `/api/mobilites/findByEtatMobilite?etat=${etat}` //url permettant d'accéder aux mobilitées filtrées par leur état

  if (etat != 'tous'){ //si on sélectionne n'importe quel état de la liste déroulante, on filtre
    getMobilitesFiltrees(url)
  } else { // on sélectionne l'option permettant d'afficher toutes les mobilités
    getMobilites(urlAllMobilites)
  }
}

onMounted(()=>{
    getMobilites(urlAllMobilites)
    
})
</script>

<style>
</style>