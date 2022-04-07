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

/**
 * 
 * @param
 * @return
 * Fonction qui ajoute un étudiant par méthode POST
 */
function addEtud() {
    //Paramètres
    let nom = document.getElementById("nom").value
    let prenom = document.getElementById("prenom").value
    let ine = document.getElementById("ine").value
    let promotion = document.getElementById("promotion").value

    const url = `http://localhost:8989/api/etudiants` // l’url de l'API

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const fetchOptions = {method:"POST", headers: myHeaders, body: JSON.stringify({nom:nom, numEtud:ine, prenom:prenom, promo:promotion})};
    fetch(url,fetchOptions)
    .then((response) => {return response.json()})
    .then((dataJSON) => {
    })
    .catch((error) => console.log(error));
}
</script>

<style>

</style>