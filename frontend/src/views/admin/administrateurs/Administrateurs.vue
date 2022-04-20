<template>
  <h1>Gestion des administrateurs</h1>
  <FormAddAdmin @addAdmin="addAdmin"/>
</template>

<script setup>
  import FormAddAdmin from './formAddAdmin/FormAddAdmin.vue'

  function addAdmin(username, email, password){
    let url = '/api/auth/signup'
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    let body = JSON.stringify({username:username._value, email:email._value, password:password._value, role:['user','mod','admin']})
    fetch(url, {method: 'POST', headers: myHeaders, body: body})
    .then((response)=>{
      return response.json()
    })
    .then((dataJson)=>{
      alert(dataJson.message)
    })
    .catch((error)=>{
      console.log('error')
    })
  }
</script>

<style>

</style>