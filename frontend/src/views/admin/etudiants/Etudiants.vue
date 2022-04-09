<template>
  <div>
  <h1>Gestion des étudiants</h1>
  <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#ajout">
    Ajouter un étudiant
  </button>
  <FormAddEtud @post="addEtud"/>

  <a data-bs-toggle="modal" data-bs-target="#modif" class="btn" type="button" > <img v-bind:src="edit" alt="edit"></a>
  <FormModifEtud/>
  
  <SearchEtud v-bind:etudiants="listeEtudiants"/>

  <TableEtud :etudiants="listeEtudiants"/>
  </div>
</template>

<script setup>
  import FormAddEtud from './formAddEtud/FormAddEtud.vue'
  import FormModifEtud from './formModifEtud/FormModifEtud.vue'
  import SearchEtud from './searchEtud/SearchEtud.vue'
  import TableEtud from './tableEtud/TableEtud.vue'
  import poubelle from '../../../img/poubelle.png'
  import edit from '../../../img/edit.png'

  import { onMounted, onUpdated } from "vue";

  import { reactive } from 'vue';
  //Liste d'étudiants
  const listeEtudiants = reactive([]);

  onMounted(() => {
    getEtud()
});

  function addEtud(nom, prenom, promotion, ine){
    const url = `http://localhost:8989/api/etudiants` 
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const fetchOptions = {method:"POST", headers: myHeaders, body: JSON.stringify({nom:nom, numEtud:ine, prenom:prenom, promo:promotion})};
    fetch(url,fetchOptions)
    .then((response) => getEtud())
    .catch((error) => console.log(error));
  }

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