<template>
   <div class="mt-5 container">
    <div class="row h-25 align-items-center">


    <h1 class="col-9 text-center">GESTION DES ADMINISTRATEURS</h1>

    <div class="col-3 text-right">
        <button type="button" class="btnOrange" data-bs-toggle="modal" data-bs-target="#ajouter">
            <img img v-bind:src="userAdd" alt="admin" class="mr-1">
            Ajouter un admin
        </button>
    </div>
        <TableAdmin @deleteAdmin="deleteAdmin" :admins="admins"/>
    </div>    
        <FormAddAdmin @addAdmin="addAdmin"/>

  </div>
</template>

<script setup>
  import { reactive } from '@vue/reactivity';
  import { onMounted } from '@vue/runtime-core';
  import FormAddAdmin from './formAddAdmin/FormAddAdmin.vue'
  import TableAdmin from './tableAdmin/TableAdmin.vue'
  import userAdd from '../../../img/user-add.png'


  /**
   * Liste des administrateurs
   */
  let admins = reactive([])

  /**
   * Fonction pour ajouter un administrateur
   */
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
      getAdmins()
      alert(dataJson.message)
    })
    .catch((error)=>{
      console.log('error')
    })
  }

  /**
   * Fonction pour récupèrer la liste des admins
   */
  function getAdmins(){
    fetch('/api/users', {method: 'GET', headers: {"Authorization": localStorage.getItem('jwt')}})
    .then((response)=>{
      return response.json()
    })
    .then((dataJson)=>{
      admins.splice(0,admins.length)
      dataJson._embedded.users.forEach((e)=>admins.push(e))
    })
  }

  /**
   * Fonction pour supprimer un admin
   */
  function deleteAdmin(id){
    fetch(`/api/users/${id}`, {method: 'DELETE', headers: {"Authorization": localStorage.getItem('jwt')}})
    .then(()=>{
      getAdmins()
    })
  }

  /**
   * On mounted
   */
  onMounted(()=>{
    getAdmins()
  })
</script>

<style>

</style>