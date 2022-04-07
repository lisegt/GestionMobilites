<template>
<h2>Ajouter un étudiant</h2>
<div id="form">
    <form @submit.prevent="addEtud()">
        <label>Nom :</label>
        <br>
        <input id="nom" name="nom" type="text" v-model="nom" placeholder="Entrez le nom de l'étudiant ..." required/>
        <br>
        <label>Prénom :</label>
        <br>
        <input id="prenom" name="prenom" type="text" v-model="prenom" placeholder="Entrez le prénom de l'étudiant ..." required/>
        <br>
        <label>INE :</label>
        <br>
        <input id="ine" name="numEtud" type="number" v-model="ine" placeholder="Entrez l'INE de l'étudiant ..." required/>
        <br>
        <label>Promotion :</label>
        <br>
        <input id="promotion" name="promo" type="number" v-model="promotion" placeholder="Sélectionnez une promotion ..." required/>
        <br>
        <input id="Bouton" type="submit" value="Valider" />
    </form>
</div>
</template>

<script setup>
import { ref } from 'vue'

//Variables
let nom = ref('');
let prenom = ref('');
let ine = ref(0);
let promotion = ref(0);

function addEtud() {
    console.log(nom + prenom + ine + promotion)
    const url = `/api/etudiants` // l’url de la ressource
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const fetchOptions = {method:"POST", headers: myHeaders, body: JSON.stringify({nom:nom, numetud:ine, prenom:prenom, promo:promotion})};
    console.log(fetchOptions.body)
    fetch(url,fetchOptions)
    .then((response) => {return response.json()})
    .then((dataJSON) => {
    })
    .catch((error) => console.log(error));
}
</script>

<style>

</style>