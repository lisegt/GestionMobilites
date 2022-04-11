<template>
  <h1>Gestion des documents administratifs</h1>

  <FormModifDocsAdmin @update_ok="getDoc"/>
  <TableDocsAdmin @update="setDoc" @delete="deleteDocument" v-bind:documents="listeDocuments"/>

</template>

<script setup>
import TableDocsAdmin from "./tableDocsAdmin/TableDocsAdmin.vue";
import FormModifDocsAdmin from './formModifDocsAdmin/FormModifDocsAdmin.vue'
import { onMounted, onUpdated } from "vue";
import { reactive } from 'vue';
//Liste de documents
const listeDocuments = reactive([]);

onMounted(() => {
  getDoc()
});

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
 * Fonction qui récupère les données pas encore modifiées
 */
function setDoc(doc){
  document.getElementById("intitule").value = doc.intitule
  document.getElementById("description").value = doc.description
  document.getElementById('idDocToEdit').value= doc.id
}
</script>

<style>

</style>