<template>
  <div class=" mt-5 container ">
    <div class="row h-25  align-items-center">

      <div class="col-4 h-50 d-flex flex-column">
        <SearchDestination v-bind:destinations="listeDestinationsTab" @searchDestination="searchDestination"/>
        
        <FiltreDestinations @searchByPays="searchByPays" @searchByTypeMobilite="searchByTypeMobilite" @searchByStatutContrat="searchByStatutContrat"/>
        
      </div>

      <h1 class="col-4 text-center">GESTION DES DESTINATIONS</h1>

      <div class="col-4 text-right">
          <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#exempleModalD">
              <img img v-bind:src="world" alt="world" class="mr-1">
              Ajouter une destination
          </button>
      </div>

    <TableDestinations 
                @set="setDestination" 
                @delete="deleteDestination" 
                v-bind:destinations="listeDestinationsTab" 
                @updateImage="encodeImageFileAsURL"
                @updateDestination="updateDestination"
                class="mt-4"/>
    </div>
  <FormAddDestination @ajouter="postDestination" @changePicture="encodeImageFileAsURL" />
  </div>
  
</template>

<script setup>

    import {reactive, onMounted,ref} from 'vue'
    import TableDestinations from './tableDestinations/TableDestinations.vue'
    import FormAddDestination from './formAddDestination/FormAddDestination.vue'
    import FiltreDestinations from './filtreDestinations/FiltreDestinations.vue'
    import SearchDestination from './searchDestination/SearchDestination.vue'

    import world from '../../../img/world.png'

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
    document.getElementById("destinationsNav").classList.add("active")
   
    const listeDestinationsTab = reactive([])
    let img = ref("")
    let idDestination = ref(0)
    const url = 'api/destinations'
    function dateDiff(date1, date2){
                var diff = {}                           // Initialisation du retour
                var tmp = date2 - date1;
                
                tmp = Math.floor(tmp/1000);             // Nombre de secondes entre les 2 dates
                diff.sec = tmp % 60;                    // Extraction du nombre de secondes
            
                tmp = Math.floor((tmp-diff.sec)/60);    // Nombre de minutes (partie entière)
                diff.min = tmp % 60;                    // Extraction du nombre de minutes
            
                tmp = Math.floor((tmp-diff.min)/60);    // Nombre d'heures (entières)
                diff.hour = tmp % 24;                   // Extraction du nombre d'heures
                
                tmp = Math.floor((tmp-diff.hour)/24);   // Nombre de jours restants
                diff.day = tmp;
                console.log(diff)
                return diff;
    }

    const urlAllDestinations = '/api/destinations';
    function getDestinations(url){
           
        listeDestinationsTab.splice(0,listeDestinationsTab.length) //On vide la liste des destinations avant de la remplir afin d'éviter les doublons

        fetch(url, {method:'GET', headers: {"Authorization": localStorage.getItem('jwt')}})
        .then((res)=>{return res.json()})
        .then((json)=>{
            let isValide;
            let date;
            let actuelDate = new Date();
        
            for(let d of json._embedded.destinations){

                    date=new Date(d.dateFinDeContratIsis)
                    
                    if(dateDiff(actuelDate,date).day<365 && dateDiff(actuelDate,date).day>0 ){
                                isValide = "Bientôt Expiré"
                    }
                    else if(dateDiff(actuelDate,date).day<0){
                        isValide = "Expiré"
                    }
                    else{
                        isValide = "Valide"
                    }
                   
                    listeDestinationsTab.push([d,isValide])
                    

            }
        })
    }

    /**
     * Méthode pour supprimer une destination
     */
    function deleteDestination(id){
         const fetchOptions = {
                method: "DELETE",
                headers: {"Authorization": localStorage.getItem('jwt')}
                };
        let url = `/api/destinations/${id}`
        fetch(url,fetchOptions)
        .then((response)=>{
          if(response.status === 409){
            toastDanger('Echec de la suppression', 'Cette destination participe a des mobilités')
          }
          else{
            getDestinations(urlAllDestinations)
            toastSuccess('Destination supprimée avec succès')
          }
        })
        .catch((err)=>{
            console.log("message d'erreur : ",err)})
    }
    

    function setDestination(destination){

        idDestination.value = destination[0].id

        document.getElementById("nomEtablissement").value=destination[0].nomEtablissementAccueil
        document.getElementById("nomVille").value=destination[0].ville
        document.getElementById("nomPays").value=destination[0].pays
        document.getElementById(`${destination[0].typeMobilite}`).selected = true
        document.getElementById("semestres").value= parseInt(destination[0].nbPlaceSemestre)
        document.getElementById("nbPlaceAnnee").value=parseInt(destination[0].nbPlaceAnnee)
        document.getElementById("dateFinContrat").value=destination[0].dateFinDeContratIsis
        img.value=destination[0].image
        
    
      }

    function updateDestination(event){
          
           event.preventDefault()
           let nomEtablissement = document.getElementById("nomEtablissement").value
           let nomVille = document.getElementById("nomVille").value
           let nomPays = document.getElementById("nomPays").value
           let typeMobilite = document.getElementById("typeMobilite").value
           let nbPlaceSemestre = document.getElementById("semestres").value
           let nbPlaceAnnee= document.getElementById("nbPlaceAnnee").value
           let date = document.getElementById("dateFinContrat").value

            const url = `/api/destinations/${idDestination.value}` // l’url de l'API
            const fetchOptions = {  method:"PUT", 
                                    headers: {"Authorization": localStorage.getItem('jwt'), "Content-Type":"application/json"}, 
                                    body: JSON.stringify({
                                        nomEtablissementAccueil:nomEtablissement,
                                        dateFinDeContratIsis:date,
                                        nbPlaceAnnee:nbPlaceAnnee,
                                        nbPlaceSemestre:nbPlaceSemestre,
                                        typeMobilite:typeMobilite,
                                        ville:nomVille,
                                        pays:nomPays,
                                        image:img.value
                                        })};
            fetch(url,fetchOptions)
            .then((response)=>{
              if(response.status === 200){
                getDestinations(urlAllDestinations)
                toastSuccess('Modification effectuée')
              }
            })
            .catch((error) => console.log(error));

    }

    
    function encodeImageFileAsURL(event) {
        
        let file = event.target.files[0];
        let reader = new FileReader();
        reader.onloadend = function() {
          
          img.value=reader.result
          console.log(img.value)
        }
        reader.readAsDataURL(file);

      }
    
    function postDestination(event){
        event.preventDefault()
        let nomEtablissement = document.getElementById("addNomEtablissement").value
        let nomVille = document.getElementById("addNomVille").value
        let nomPays = document.getElementById("addNomPays").value
        let typeMobilite = document.getElementById("addTypeMobilite").value
        let nbPlaceSemestre = document.getElementById("addSemestres").value
        let nbPlaceAnnee= document.getElementById("addNbPlaceAnnee").value
        let date = document.getElementById("addDateFinContrat").value
        
        //Si les champs sont ok
        console.log(nomEtablissement)
        if(nomEtablissement && nomVille && nomPays ){
          const url = `/api/destinations` // l’url de l'API
          const fetchOptions = {  method:"POST", 
                                  headers: {"Content-Type":"application/json","Authorization": localStorage.getItem('jwt')}, 
                                  body: JSON.stringify({
                                      nomEtablissementAccueil:nomEtablissement,
                                      dateFinDeContratIsis:date,
                                      nbPlaceAnnee:nbPlaceAnnee,
                                      nbPlaceSemestre:nbPlaceSemestre,
                                      typeMobilite:typeMobilite,
                                      ville:nomVille,
                                      pays:nomPays,
                                      image:img.value
                                      
                                })};
            fetch(url,fetchOptions)
            .then((response)=>{
              if(response.status === 400){
                toastDanger("Echec de l'ajout", "Des champs sont manquants")
              }
              else{
                getDestinations(urlAllDestinations)
                toastSuccess('Destination ajoutée avec succès')
                clear()
              }
            })
            .catch((error) => console.log(error));
        }
        // Si les champs saisie ne sont pas valides
        else{
          toastDanger("Echec de l'ajout", "Des champs sont manquants")
        }
            
    }
  /**
   * Pour enlever les champs de l'ajout précedent
   */
  function clear(){
    document.getElementById('addNomEtablissement').value=''
    document.getElementById('addNomVille').value=''
    document.getElementById('addNomPays').value=''
    document.getElementById('addSemestres').value=''
    document.getElementById('addNbPlaceAnnee').value=''
    document.getElementById('addDateFinContrat').value=''
    document.getElementById('addImage').value=''
}
   
/**
 * @param pays sélectionné dans la liste déroulante
 * fonction qui permet de récupérer toutes les destinations dans le pays sélectionné
 */
function searchByPays(pays){

  const fetchOptions = { method: "GET",headers: {"Authorization": localStorage.getItem('jwt')} }; //on utilise l'opération GET car on veut récupérer les destinations filtrées par pays
  const url = '/api/destinations/search/findByPays?pays='+pays //url permettant d'accéder aux destinations filtrées par pays

  if(pays != 'tous'){ //si on sélectionne n'importe quel pays de la liste déroulante, on filtre
    fetch(url, fetchOptions)
      .then((response) => { return response.json();})
      .then((dataJSON) => {
          getDestinations(url)
      })
      .catch((error) => console.log(error));
  } else { // on sélectionne l'option permettant d'afficher toutes les destinations
    getDestinations(urlAllDestinations)
  }
}

/**
 * @param type sélectionné dans la liste déroulante
 * fonction qui permet de récupérer toutes les destinations associées à un type de mobilité sélectionné
 */
function searchByTypeMobilite(type){

  const fetchOptions = { method: "GET", headers: {"Authorization": localStorage.getItem('jwt')} }; //on utilise l'opération GET car on veut récupérer les destinations filtrées par type de mobilité
  const url = '/api/destinations/search/findByTypeMobilite?type='+type //url permettant d'accéder aux destinations filtrées par type de mobilité

  if(type != 'tous'){ //si on sélectionne n'importe quel type de mobilité de la liste déroulante, on filtre
    fetch(url, fetchOptions)
      .then((response) => { return response.json();})
      .then((dataJSON) => {
          getDestinations(url)

      })
      .catch((error) => console.log(error));
  } else { // on sélectionne l'option permettant d'afficher toutes les destinations
    getDestinations(urlAllDestinations)
  }
}

/**
 * @param statut sélectionné dans la liste déroulante
 * fonction qui permet de récupérer toutes les destinations associées à un statut de contrat sélectionné
 */
function searchByStatutContrat(statut){

  const fetchOptions = { method: "GET", headers: {"Authorization": localStorage.getItem('jwt')} }; //on utilise l'opération GET car on veut récupérer les destinations filtrées par statut de contrat
  const url = '/api/destinations/findByStatutContrat?statut='+statut //url permettant d'accéder aux destinations filtrées par statut de contrat

  if(statut != 'tous'){ //si on sélectionne n'importe quel statut de la liste déroulante, on filtre
    fetch(url, fetchOptions)
      .then((response) => { return response.json();})
      .then((dataJSON)=>{
        listeDestinationsTab.splice(0, listeDestinationsTab.length)
        dataJSON.forEach((destination)=>{
          let isValide;
          let date;
          let actuelDate = new Date();

          date=new Date(destination.dateFinDeContratIsis)
                    
          if(dateDiff(actuelDate,date).day<365 && dateDiff(actuelDate,date).day>0 ){
            isValide = "Bientôt Expiré"
          }
          else if(dateDiff(actuelDate,date).day<0){
            isValide = "Expiré"
          }
          else{
              isValide = "Valide"
          }
          listeDestinationsTab.push([destination,isValide])
        })
      })
      .catch((error) => console.log(error));
  } else { // on sélectionne l'option permettant d'afficher toutes les destinations
    getDestinations(urlAllDestinations)
  }
}

/**
 * Fonction qui filtre les destinations en fonction d'une chaîne de caractères saisie par l'utilisateur
 */

function searchDestination(inputUser){
  const url = `/api/destinations/search/findAllByNomEtablissementAccueilContainingIgnoreCaseOrVilleContainingIgnoreCase?nom=${inputUser}&ville=${inputUser}`
  fetch(url, {method: 'GET', headers: {"Authorization": localStorage.getItem('jwt')}})
  .then((result)=>{
    return result.json()
  })
  .then((dataJson)=>{
    getDestinations(url) //on récupère les destinations filtrées
  })
}

/**
 * lorsqu'on crée le composant Destinations, on exécute la fonction getDestinations(url) qui charge toutes les destinations de la BDD
 */
onMounted(()=>{ getDestinations(urlAllDestinations) })

  /**
   * Fonction pour affichage de toast
   */

  function toastSuccess (message) {
      createToast(message, {type: 'success'})
  }

  function toastDanger (title, message) {
      createToast({ title: title, description: message}, {type: 'danger'})
  }
</script>

<style>
</style>