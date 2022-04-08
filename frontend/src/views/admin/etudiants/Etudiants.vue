<template>
<<<<<<< HEAD
  <div>
    <h1>Gestion des étudiants</h1>

    <!-- Button trigger modal -->
    <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#ajout">
      Ajouter un étudiant
    </button>
    <FormAddEtud/>
    
    <SearchEtud v-bind:etudiants="listeEtudiants"/>
  </div>
=======
  
  <h1>Gestion des étudiants</h1>

  <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#ajout">
    Ajouter un étudiant
  </button>
  <FormAddEtud/>

  <a data-bs-toggle="modal" data-bs-target="#modif" class="btn" type="button" > <img v-bind:src="edit" alt="edit"></a>
  <FormModifEtud/>
  
  <SearchEtud v-bind:etudiants="listeEtudiants"/>

>>>>>>> 01a13ee47c1ad4b88528c04c1faf6ff8c266df43
</template>

<script setup>
  import FormAddEtud from './formAddEtud/FormAddEtud.vue'
  import FormModifEtud from './formModifEtud/FormModifEtud.vue'
  import SearchEtud from './searchEtud/SearchEtud.vue'
  import poubelle from '../../../img/poubelle.png'
  import edit from '../../../img/edit.png'

  import { onMounted, onUpdated } from "vue";

  import { reactive } from 'vue';
  //Liste d'étudiants
  const listeEtudiants = reactive([]);

  onMounted(() => {
    getEtud()
});

/**
 * 
 * @param
 * @return
 * Fonction qui remplit la liste listeEtudiants
 */
function getEtud(){
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