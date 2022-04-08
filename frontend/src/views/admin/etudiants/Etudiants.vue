<template>
  
  <h1>Gestion des étudiants</h1>

  <!-- Button trigger modal -->
  <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#ajout">
    Ajouter un étudiant
  </button>
  <FormAddEtud/>
  
  <SearchEtud v-bind:etudiants="listeEtudiants"/>

</template>

<script setup>
  import FormAddEtud from './formAddEtud/FormAddEtud.vue'
  import FormModifEtud from './formModifEtud/FormModifEtud.vue'
  import SearchEtud from './searchEtud/SearchEtud.vue'

  import { onMounted, onUpdated } from "vue";

  import { reactive } from 'vue';
  //Liste d'étudiants
  const listeEtudiants = reactive([]);

  onMounted(() => {
    getToDos()
});

function getToDos(){
    const fetchOptions = {method:"GET"};
    fetch("http://localhost:8989/api/etudiants/",fetchOptions)
    .then((response) => {return response.json()})
    .then((dataJSON) => {
      listeEtudiants.splice(0,listeEtudiants.length)
      dataJSON._embedded.etudiants.forEach((e)=>listeEtudiants.push(e))
      console.log(listeEtudiants)
    })
    .catch((error) => console.log(error));
}
  
</script>

<style scoped>

</style>