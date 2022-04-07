<template>
<h2>Ajouter un étudiant</h2>
<div id="form">
    <form @submit.prevent="AddEtud">
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
//Variables
let nom;
let prenom;
let ine;
let promotion;

let AddEtud = function() {
    console.log("test")
}
</script>

<style>

</style>