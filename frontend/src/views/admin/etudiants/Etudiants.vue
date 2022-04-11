<template>
  <div>
  <h1>Gestion des étudiants</h1>
  <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#ajout">
    Ajouter un étudiant
  </button>
  <FormAddEtud @post="addEtud"/>
  <FormModifEtud :etudiant="etudToEdit" @update_ok="getEtud"/>
  
  <SearchEtud v-bind:etudiants="listeEtudiants"/>

  <TableEtud :etudiants="listeEtudiants" @delete="deleteEtud" @update="editEtud"/>
  </div>
</template>

<script setup>
  import FormAddEtud from './formAddEtud/FormAddEtud.vue'
  import FormModifEtud from './formModifEtud/FormModifEtud.vue'
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