<template>
  <h1>Gestion des documents administratifs</h1>

  <TableDocsAdmin @set="setDoc" @delete="deleteDocument" v-bind:documents="listeDocuments"/>

</template>

<script setup>
import TableDocsAdmin from "./tableDocsAdmin/TableDocsAdmin.vue";
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
  let intitule = document.getElementById("intitule").value = doc.intitule
  let description = document.getElementById("description").value = doc.description

  document.getElementById("Bouton").addEventListener('click',()=>{updateEtud(doc)})
}

/**
 * 
 * @param doc
 * @return
 * Fonction qui modifie un étudiant par méthode PUT
 */
function updateEtud(doc) {
  let intitule = document.getElementById("intitule").value
  let description = document.getElementById("description").value

  const url = `http://localhost:8989/api/documents/` //l’url de l'API

  let myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  const fetchOptions = {method:"PUT", headers: myHeaders, body: JSON.stringify({intitule:doc.intitule, description:doc.description})};
  fetch(url+`${doc.id}`,fetchOptions)
  .catch((error) => console.log(error));
}
</script>

<style>

</style>