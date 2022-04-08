<template>
<div id="form">
    <form @submit.prevent="search()">
        <div class="form-group">
            <input id="search" class="form-control" name="nom" type="text" v-model="nom" placeholder="Entrez le nom de l'étudiant ..." required/>
        </div>
        <input id="Bouton" type="submit" value="Search" />
    </form>
</div>
</template>

<script setup>
/**
 * @param
 * @return
 * Fonction qui recherche un étudiant avec son nom
 */

import { reactive } from 'vue';
//Liste reactive pour la recherche :
const etudiantSearch = reactive([]);

let etudiants= defineProps(["etudiants"]);

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