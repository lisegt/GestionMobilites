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

  <TableMobilites @addDoc="addDoc" @delete="deleteMobilite" v-bind:mobilites="listeMobilitesTab" class="mt-4 mb-5"/>
  
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

    import { createToast } from 'mosha-vue-toastify';
    import 'mosha-vue-toastify/dist/style.css'

    /**
     * Activer les items de la navbar selon la page consultée
     * On retire l'attribut de tous les items de la nav et on le rajoute à l'item de la page concernée
    **/
    let listeNav = ["accueilNav","etudiantNav","destinationsNav","mobilitesNav","docNav","siteNav"]
    for(let l of listeNav){
    document.getElementById(l).classList.remove("active")
    }
    document.getElementById("mobilitesNav").classList.add("active")

    
    const listeMobilitesTab = reactive([])
    let mobilite = ref({})
    let file =ref("")
    /**
     * 
     * @param  date1 date actuelle
     * fonction qui permet de calculer le nombre de jours entre la date actuel et la date de retour de l'etudiant
     */
   function dateDiff(date1){
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

  /**
   * 
   * @param url vers lequel les requêtes fetch sont envoyées
   * fonction qui permet de récuperer les mobilités stockées en base de données
   */
  function getMobilites(url){
              
        listeMobilitesTab.splice(0,listeMobilitesTab.length) //On vide la liste des mobilités avant de la remplir afin d'éviter les doublons

        
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
                      
                      listeMobilitesTab.push([d,etudiant,destination,"Non Validée"])
                    }
                    else{
                      if(dateDiff(d.dateDepart,d.dureeEnMois)<0){
                          
                          listeMobilitesTab.push([d,etudiant,destination,"Validée"])
                      }
                      else{
                        
                        listeMobilitesTab.push([d,etudiant,destination,"En cours"])
                      }}
                    })
                  })  
            }
        })
  }

  /**
     * 
     * @param id  de la mobilité à supprimer
     * fonction qui permet de supprimer une mobilité 
     * elle fait appel à la methode @function getMobilites() afin de mettre à jour la liste des mobilités
     */
  function deleteMobilite(id){
      const fetchOptions = {
              method: "DELETE",
              headers: {"Authorization": localStorage.getItem('jwt'), "Content-Type":"application/json"}
              };
      let url = `http://localhost:8989/api/mobilites/${id}`
      fetch(url,fetchOptions)
      .then((response)=>{
        if(response.status === 204){
            getMobilites(urlAllMobilites)
            toastSuccess('Mobilité supprimée')
        }
        else{
            toastDanger('Echec de la suppression')
        }
      })
      .catch((err)=>{
          window.alert("Vous ne pouvez pas supprimer une destination qui est liée à une ou plusieurs mobilités")
          console.log("message d'erreur : ",err)})
  }
    
  /**
     * 
     * @param event lors de l'upload d'un fichier
     * fonction qui permet de convertir un fichier en base64 
     */
  function setFile(event){
    let reader = new FileReader();
    reader.onloadend = function() {
          
          file.value=reader.result
          
        }
    reader.readAsDataURL(event.target.files[0]);
  }

  /**
   * 
   * @param mobi : mobilité selectionné
   * fonctionne qui assigne à l'objet ref la mobilité en paramètre afin de la recupérer 
   * dans le formulaire permetant d'ajouter un document
   */
  function addDoc(mobi){
      mobilite.value=mobi
      file.value=""
      document.getElementById("file").value=""
  }

  /**
   * 
   * @param event lors du clique sur le bouton de modification
   * fonction qui met à jour la mobilité qui est dans le formulaire de modification
   */
  function updateMobilite(event){
    
    event.preventDefault()
    if(file.value!=""){
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
      .then((response)=>{
        if(response.status === 200){
          getMobilites(urlAllMobilites)
          toastSuccess('Mobilité complétée')
        }
      })
      .catch((error) => console.log(error));
  }
  else{
    toastDanger("Retour d'expérience","Auncun fichier selectionné")
  }
  }
  
  /**
   * fonction qui ajoute une mobilité à la base de donnée, lorsque le formulaire d'ajout est validé
   */
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
      .then((response)=>{
        if(response.status === 201){
            toastSuccess('Mobilité ajoutée')
            getMobilites()
            window.location.reload() //Grosse fraude (mais ça marche)
        }
        else{
            toastDanger("La mobilité n'a pas pu être ajoutée")
        }
        clear()
      })
      .catch((error) =>{ 
        console.log(error)
        window.alert(date)});
  }

  /**
   * Fonction pour netoyer les champs de l'ajout précedent
   */
  function clear(){
      let idDestination = document.getElementById("addDestination").value = ''
      let idEtudiant = document.getElementById("selectEtud").value = ''
      let date = document.getElementById("addDepart").value = ''
      let duree = document.getElementById("addDuree").value = ''
      let periode = document.getElementById("periode").value  = ''
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

/**
 * @param etudiant : nom ou prénom d'un étudiant saisi par l'utilisateur
 * fonction qui permet de récupérer toutes les mobilités concernant un étudiant saisi
 */
function searchMobilite(etudiant){
  const url = `/api/mobilites/findByEtudiant?etudiant=${etudiant}` //url permettant d'accéder aux mobilités filtrées par promo
  if(etudiant != ''){ //si on sélectionne n'importe quelle promo de la liste déroulante, on filtre

    getMobilitesFiltrees(url) //on récupère les mobilités filtrées

  } else { // on sélectionne l'option permettant d'afficher toutes les mobilités

    getMobilites(urlAllMobilites)
  }
}

onMounted(()=>{
    getMobilites(urlAllMobilites)
    
})

/**
 * 
 * @param  message lors d'un evenement
 * fonction qui retourne un message à l'utilisateur lors d'une requête qui a fonctionnée
 */
function toastSuccess (message) {
      createToast(message, {type: 'success'})
  }

/**
 * 
 * @param  title lors d'un evement
 * @param  message lors d'un evenement
 * fonction qui retourne un message à l'utilisateur lors d'une requête qui n'a pas fonctionnée
 */
function toastDanger (title, message) {
    createToast({ title: title, description: message}, {type: 'danger'})
}
</script>

<style>
</style>