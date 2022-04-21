<template>
<div class=" mt-5 container ">
  <div class="row h-25 align-items-center">
    <div class="col-4"></div>

    <h1 class="col-4 text-center">Gestion des documents administratifs</h1>
    
    <div class="col-4 text-right">
      <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#ajout">
        <img img v-bind:src="ajoutDoc" alt="ajouter document" class="mr-1">
        Ajouter un document
      </button>
    </div>
    <FormAddDocsAdmin @post="addDoc" @changeFile="setFile"/>

    <TableDocsAdmin @update="setDoc" 
    @updateFile="setFile" 
    @updateDoc="updateDoc" 
    @delete="deleteDocument" 
    v-bind:documents="listeDocuments"
    class="mt-4"/>
  </div>
</div>
</template>

<script setup>
import TableDocsAdmin from "./tableDocsAdmin/TableDocsAdmin.vue";
import FormModifDocsAdmin from './formModifDocsAdmin/FormModifDocsAdmin.vue'
import FormAddDocsAdmin from './formAddDocsAdmin/FormAddDocsAdmin.vue'
import { onMounted, onUpdated } from "vue";
import { reactive,ref } from 'vue';
import { createToast } from 'mosha-vue-toastify';
import 'mosha-vue-toastify/dist/style.css'

import ajoutDoc from '../../../img/addDoc.png'

/**
 * Activer les items de la navbar selon la page consultée
 * On retire l'attribut de tous les items de la nav et on le rajoute à l'item de la page concernée
**/
let listeNav = ["accueilNav","etudiantNav","destinationsNav","mobilitesNav","docNav","siteNav"]
for(let l of listeNav){
document.getElementById(l).classList.remove("active")
}
document.getElementById("docNav").classList.add("active")

//Liste de documents
const listeDocuments = reactive([]);
let file = ref("")
let idDoc = ref(0)

/**
 * Fonction qui remplit la liste listeDocuments
 */
function getDoc(){
  const fetchOptions = {method:"GET", headers: {"Authorization": localStorage.getItem('jwt')}};
  fetch("/api/documents/",fetchOptions)
  .then((response) => {return response.json()})
  .then((dataJSON) => {
  listeDocuments.splice(0,listeDocuments.length)
  dataJSON._embedded.documents.forEach((d)=>listeDocuments.push(d))
  })
  .catch((error) => console.log(error));
}

/**
 * @param id
 * Fonction qui permet de supprimer un document à partir de son id
 */
function deleteDocument(id){
  let url = `http://localhost:8989/api/documents/`
  
  const fetchOptions = {method: "DELETE", headers: {"Authorization": localStorage.getItem('jwt')}};
  fetch(url+`${id}`,fetchOptions)
  .then((response)=>{
    if(response.status === 204){
      toastSuccess('Document supprimé avec succès')
      getDoc()
    }
    else{
      toastDanger('Impossible de supprimer le document')
    }
  })
  .catch((error)=>{console.log(error)})
}

/**
 * @param doc
 * Fonction qui modifie les données
 */
function setDoc(doc){
  idDoc.value = doc.id
 
  document.getElementById("updateIntitule").value = doc.intitule
  document.getElementById("updateDescription").value = doc.description
  file.value=doc.fichier
  document.getElementById("updateFile").value = ""
}

/**
 * @param event
 * Fonction qui permet de modifier un document (ajout d'un nouveau)
 */
function updateDoc(event){
  event.preventDefault()
  let intitule = document.getElementById("updateIntitule").value 
  let description = document.getElementById("updateDescription").value

  let myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  myHeaders.append("Authorization", localStorage.getItem('jwt'));
  const fetchOptions = {  method:"PUT", headers: myHeaders, body: JSON.stringify({intitule:intitule,description:description,fichier: file.value})};
  fetch(`/api/documents/${idDoc.value}`,fetchOptions)
  .then((response)=>{
    if(response.status === 200){
      getDoc()
      toastSuccess("Document modifié avec succès")
    }
    else{
      toastDanger("Impossible de modifier ce document")
    }
  })   
  .catch((err)=>{ console.log("erreur: ",err)}) 
}

/**
 * @param event 
 * evenement du @change
 * Fonction qui convertis un fichier pdf en string base64
 */
function setFile(event){
  let reader = new FileReader();
  reader.onloadend = function() {
        file.value=reader.result
      }
  reader.readAsDataURL(event.target.files[0]);
}

/**
 * @param event
 * Fonction qui permet d'ajouter un document
 */
function addDoc(event){
  event.preventDefault()
  const urlPost = `/api/documents`

  let intitule = document.getElementById("addIntitule").value
  let desc = document.getElementById("addDescription").value
  let myHeaders = new Headers();

  myHeaders.append("Content-Type", "application/json");
  myHeaders.append("Authorization",localStorage.getItem('jwt'))

  const fetchOptions = {method:"POST", headers: myHeaders, body: JSON.stringify({
    intitule:intitule, 
    description:desc,
    fichier:file.value})};

  if(intitule && desc){
    fetch(urlPost,fetchOptions)
    .then((response) => {
      if(response.status === 201){
        toastSuccess('Document ajouté')
        getDoc()
      }
      else{
        toastDanger("Impossible d'ajouter ce document")
      }
    })
    .catch((error) => console.log(error));
  }
  else{
    toastDanger("Echec de l'ajout", 'Des champs sont manquants')
  }
  document.getElementById("addIntitule").value = ''
  document.getElementById("addDescription").value = ''
}

/**
 * Lorsqu'on crée le composant DocsAdmin, on exécute la fonction getDoc()
 */
onMounted(() => {
  getDoc()
});

/**
* Fonction pour affichage de toast
*/
function toastSuccess (message) {
    createToast(message, {type: 'success'})
}

/**
 * @param title
 * @param message
* Fonction pour affichage de toast
*/
function toastDanger (title, message) {
    createToast({ title: title, description: message}, {type: 'danger'})
}
</script>

<style>

</style>