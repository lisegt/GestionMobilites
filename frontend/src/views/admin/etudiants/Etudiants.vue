<template>
  <h1>Gestion des étudiants</h1>
  <TableEtud :etudiants="etudiants"/>
  <FormAddEtud />
</template>

<script setup>
  import FormAddEtud from './formAddEtud/FormAddEtud.vue'
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
  
</script>

<style>

</style>