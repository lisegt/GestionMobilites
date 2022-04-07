<template>
  
  <h1>Gestion des étudiants</h1>
  <button @click="test">test</button>

  <FormAddEtud/>
</template>

<script setup>
  import FormAddEtud from './formAddEtud/FormAddEtud.vue'
  import FormModifEtud from './formModifEtud/FormModifEtud.vue'

  import { reactive } from 'vue';
  const listeEtudiants = reactive([]);
  

  function test(){
    fetch("/api/etudiant/mobilite/1", {method: 'GET'})
    .then((response)=>response.json())
    .then((dataJson)=> console.log(dataJson))
  }
  
</script>

<style>

</style>