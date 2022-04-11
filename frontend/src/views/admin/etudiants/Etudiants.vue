<template>
  <div class="h-100 container">
    <div class="row h-25 align-items-center">

      <div class="col-4 h-50  d-flex flex-column justify-content-around align-items-left">
        <SearchEtud v-bind:etudiants="listeEtudiants" class="w-100"/>
        <div class="filtreTab w-100 d-flex  ">
          <div class="dropdown w-100">
            <FiltreEtud/>
          </div>
        </div>
      </div>

    <h1 class="col-4 text-center">GESTION DES ETUDIANTS</h1>

    <div class="col-4 text-right">
        <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#ajout">
          <img src="../../../img/ajoutEtudiant.png" alt="logo ajout étudiant" height="20"/>
           Ajouter un étudiant
        </button>
    </div>
      
      <FormAddEtud @post="addEtud"/>
      <FormModifEtud @update_ok="getEtud"/>
      
      
    </div>

    <TableEtud :etudiants="listeEtudiants" @delete="deleteEtud" @update="editEtud"/>
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
  import { onMounted, onUpdated} from "vue";
  import { reactive } from 'vue';

  //Liste d'étudiants
  const listeEtudiants = reactive([]);

  function addEtud(nom, prenom, promotion, ine){
    const url = `/api/etudiants` 
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const fetchOptions = {method:"POST", headers: myHeaders, body: JSON.stringify({nom:nom, numEtud:ine, prenom:prenom, promo:promotion})};
    fetch(url,fetchOptions)
    .then((response) => getEtud())
    .catch((error) => console.log(error));
  }

  function deleteEtud(id){
    fetch(`/api/etudiants/${id}`,{method:'DELETE'})
    .then(getEtud())
  }

  function getEtud(){
    const fetchOptions = {method:"GET"};
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

</script>

<style scoped>

</style>