<template>
<div>
  <h1>Gestion des documents administratifs</h1>
  <FormModifDocsAdmin @update_ok="getDoc"/>

  <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#ajout">
    Ajouter un document
  </button>
  <FormAddDocsAdmin @post="addDoc"/>

  <TableDocsAdmin @update="setDoc" @delete="deleteDocument" v-bind:documents="listeDocuments"/>

</div>
</template>

<script setup>
import TableDocsAdmin from "./tableDocsAdmin/TableDocsAdmin.vue";
import FormModifDocsAdmin from './formModifDocsAdmin/FormModifDocsAdmin.vue'
import FormAddDocsAdmin from './formAddDocsAdmin/FormAddDocsAdmin.vue'
import { onMounted, onUpdated } from "vue";
import { reactive } from 'vue';
//Liste de documents
const listeDocuments = reactive([]);

/**
 * 
 * @param
 * @return
 * Fonction qui remplit la liste listeDocuments
 */
function getDoc(){
  const fetchOptions = {method:"GET"};
  fetch("http://localhost:8989/api/documents/",fetchOptions)
  .then((response) => {return response.json()})
  .then((dataJSON) => {
  listeDocuments.splice(0,listeDocuments.length)
  dataJSON._embedded.documents.forEach((d)=>listeDocuments.push(d))
  console.log(listeDocuments)
  })
  .catch((error) => console.log(error));
}

/**
 * 
 * @param id
 * @return
 * Fonction qui permet de supprimer un document à partir de son id
 */
function deleteDocument(id){
  let url = `http://localhost:8989/api/documents/`
  
  let myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  const fetchOptions = {method: "DELETE", headers: myHeaders};
  fetch(url+`${id}`,fetchOptions)
  .then(()=>{getDoc()})
  .catch((error)=>{console.log(error)})
}

/**
 * 
 * @param doc
 * @return
 * Fonction qui modifie les données
 */
function setDoc(doc){
  document.getElementById("intitule").value = doc.intitule
  document.getElementById("description").value = doc.description
  document.getElementById('idDocToEdit').value= doc.id
}

/**
 * 
 * @param intitule
 * @param description
 * @return
 * Fonction qui permet d'ajouter un étudiant
 */
function addDoc(intitule, description){
  const url = `/api/documents`
  let myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  const fetchOptions = {method:"POST", headers: myHeaders, body: JSON.stringify({intitule:intitule, description:description})};
  fetch(url,fetchOptions)
  .then((response) => getDoc())
  .catch((error) => console.log(error));
}

onMounted(() => {
  getDoc()
});
</script>

<style>

</style>