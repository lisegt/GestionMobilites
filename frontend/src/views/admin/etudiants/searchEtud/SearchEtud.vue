<template>
<div id="form">
    <form @submit.prevent="search()">
        <input id="search" type="text" v-model="nom" placeholder="Rechercher un étudiant ..." required/>
        <br>
        <input id="Bouton" type="submit" value="Search" />
    </form>
</div>
</template>

<script setup>
/**
 * @param
 * @return
 * Fonction qui recherche un étudiant par méthode GET avec son nom
 */

import { reactive } from 'vue';
//Liste reactive pour la recherche :
const etudiantSearch = reactive([]);

let etudiants= defineProps(["etudiants"]);
console.log(etudiants.etudiants)
function search(){
    etudiantSearch.splice(0,etudiantSearch.length)
    let nom = document.getElementById("search").value
    
    for(let e of etudiants.etudiants){
        if(e.nom.includes(nom)){
            etudiantSearch.push(e)
        }
    }
    console.log(etudiantSearch)
}
</script>

<style>

</style>