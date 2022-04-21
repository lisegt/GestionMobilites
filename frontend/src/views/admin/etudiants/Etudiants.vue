<template>
  <div class="mt-5 container">
    <div class="row h-25 align-items-center">

      <div class="col-4 h-50  d-flex flex-column justify-content-around align-items-left">
        <SearchEtud v-bind:etudiants="listeEtudiants" @searchEtud="searchEtud"/>
        
        <FiltreEtud @searchByEtatMobilite="searchByEtatMobilite" @searchByPromo="searchByPromo"/>
        
      </div>
    <h1 class="col-4 text-center">GESTION DES ETUDIANTS</h1>
    <div class="col-4 text-right">
        <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#ajout">
          <img img v-bind:src="userAdd" alt="etudiant" class="mr-1">
          Ajouter un étudiant
        </button>
    </div>

    <TableEtud :etudiants="listeEtudiants" @delete="deleteEtud" @update="editEtud" class="mt-4"/>
    
    </div>

    
      
    <FormAddEtud @post="addEtud"/>
    <FormModifEtud @update_ok="getEtud"/>
  </div>
</template>

<script setup>
  import FormAddEtud from './formAddEtud/FormAddEtud.vue'
  import FormModifEtud from './formModifEtud/FormModifEtud.vue'
  import FiltreEtud from './filtreEtud/FiltreEtud.vue'
  import SearchEtud from './searchEtud/SearchEtud.vue'
  import TableEtud from './tableEtud/TableEtud.vue'
  import poubelle from '../../../img/poubelle.png'
  import edit from '../../../img/edit.png'
  import userAdd from '../../../img/user-add.png'

  import { onMounted, onUpdated} from "vue";
  import { reactive } from 'vue';
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
  document.getElementById("etudiantNav").classList.add("active")


  //Liste d'étudiants
  let listeEtudiants = reactive([]);

  function addEtud(nom, prenom, promotion, ine){
    const url = `/api/etudiants` 
    const fetchOptions = {method:"POST", headers:{"Content-Type" : "application/json", "Authorization": localStorage.getItem('jwt')}, body: JSON.stringify({nom:nom, numEtud:ine, prenom:prenom, promo:promotion})};
    fetch(url,fetchOptions)
    .then((response) => {
      if(response.status === 400){
        toastDanger('Champs manquants', "L'étudiant n'a pas pu être enregistré")
      }
      else{
        getEtud()
        toastSuccess('Etudiant ajouté avec succès')
      }
    })
    .catch((error) => console.log("erreur"));
  }

  function deleteEtud(id){
    fetch(`/api/etudiants/${id}`,{method:'DELETE', headers: {"Authorization": localStorage.getItem('jwt')}})
    .then((response)=>{
      //Quand on essaye de supprimer un étudiant qui a une mobilité
      if(response.status === 409){
        toastDanger('Echec de la suppression', 'Cet étudiant participe à une mobilité')
      }
      //Quand l'étudiant n'a pas de mobilité
      else{
        getEtud()
        toastSuccess('Etudiant supprimé avec succés')
      }
    })
  }

  /**
   * Fonction pour récupérer les étudiants
   */
  function getEtud(){
    const fetchOptions = {method:"GET", headers: {"Authorization": localStorage.getItem('jwt')}};
    fetch("/api/etudiants/",fetchOptions)
    .then((response) => {return response.json()})
    .then((dataJSON) => {
      listeEtudiants.splice(0,listeEtudiants.length)
      dataJSON._embedded.etudiants.forEach((e)=>listeEtudiants.push(e))
    })
    .catch((error) => console.log(error));
  }


  function editEtud(etud){
    document.getElementById('nomEtudToEdit').value=etud.nom
    document.getElementById('prenomEtudToEdit').value=etud.prenom
    document.getElementById('ineEtudToEdit').value=etud.numEtud
    document.getElementById('promotionEtudToEdit').value=etud.promo
    document.getElementById('idEtudToEdit').value= etud.id
  }

  onMounted(() => {
      getEtud()
  });

  //Fonction pour filtrage

  function searchByEtatMobilite(etat){
    if(etat === "val"){
      fetch('/api/findEtudiant/etatMobilite/val', {method: 'GET', headers: {"Authorization": localStorage.getItem('jwt')}})
      .then((result)=>{
        return result.json()
      })
      .then((dataJson)=>{
        listeEtudiants.splice(0, listeEtudiants.length)
        dataJson.forEach((item)=>{listeEtudiants.push(item)})
      })      
    }
    if(etat === "nVal"){
      fetch('/api/findEtudiant/etatMobilite/nVal', {method: 'GET', headers: {"Authorization": localStorage.getItem('jwt')}})
      .then((result)=>{
        return result.json()
      })
      .then((dataJson)=>{
        listeEtudiants.splice(0, listeEtudiants.length)
        dataJson.forEach((item)=>{listeEtudiants.push(item)})
      })    
    }
    if(etat === "tous"){
      getEtud()
    }
  }

  function searchByPromo(promo){
    if(promo != 0){
      fetch(`/api/findEtudiant/promo/${promo}`,{method: 'GET', headers: {"Authorization": localStorage.getItem('jwt')}})
      .then((result)=>{
        return result.json()
      })
      .then((dataJson)=>{
        listeEtudiants.splice(0, listeEtudiants.length)
        dataJson.forEach((item)=>{listeEtudiants.push(item)})
      })
    }
    else{
      getEtud()
    }
    
  }
  
  /**
   * Fonction qui filtre les étudiants en fonction d'une chaîne de caractères saisie par l'utilisateur
   */
  function searchEtud(inputUser){
    const url = `/api/etudiants/search/findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase?nom=${inputUser}&prenom=${inputUser}`
    fetch(url, {method: 'GET', headers: {"Authorization": localStorage.getItem('jwt')}})
    .then((result)=>{
      return result.json()
    })
    .then((dataJson)=>{
      listeEtudiants.splice(0, listeEtudiants.length)
      dataJson._embedded.etudiants.forEach((item)=>{listeEtudiants.push(item)})
    })
  }

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