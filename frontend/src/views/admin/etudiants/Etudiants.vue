<template>
  
  <h1>Gestion des étudiants</h1>
<<<<<<< HEAD
  <TableEtud :etudiants="etudiants"/>
  <FormAddEtud />
=======

  <!-- Button trigger modal -->
  <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#ajout">
    Ajouter un étudiant
  </button>
  <FormAddEtud/>
  
  <SearchEtud v-bind:etudiants="listeEtudiants"/>

>>>>>>> 2c70a52845744a3534d5dbd5166d979bea5d9e9a
</template>

<script setup>
  import FormAddEtud from './formAddEtud/FormAddEtud.vue'
<<<<<<< HEAD
  import TableEtud from './tableEtud/TableEtud.vue'
  import {onMounted, reactive} from 'vue'

  let etudiants = reactive([])

  function fetchEtudiants(){
    fetch("/api/etudiants", {method: 'GET'})
    .then((response)=>response.json())
    .then((dataJson)=> etudiants = dataJson._embedded.etudiants)
  }

  onMounted(()=>{
    fetchEtudiants()
  })

  function test(){
    console.log(etudiants)
  }
=======
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
>>>>>>> 2c70a52845744a3534d5dbd5166d979bea5d9e9a
  
</script>

<style scoped>
.btn{
    background-color: #B74803;
    border-radius: 30px;
    font-family: 'Bebas Neue';
    text-transform: uppercase;
    color: white;
    font-size: 24px;
}
</style>