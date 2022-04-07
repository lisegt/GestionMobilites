<template>
  <h1>Etudiant</h1>
  <button @click="test">test</button>
  <FormAddEtud/>
</template>

<script setup>
  import FormAddEtud from './formAddEtud/FormAddEtud.vue'

  function test(){
    fetch("/api/etudiant/mobilite/1", {method: 'GET'})
    .then((response)=>response.json())
    .then((dataJson)=> console.log(dataJson))
  }
  
</script>

<style>

</style>