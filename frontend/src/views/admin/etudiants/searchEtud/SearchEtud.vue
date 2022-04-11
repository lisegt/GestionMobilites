<template>
<div id="form">
    <form @submit.prevent="search()">
        <div class="form-group formRecherche">
            <input id="search" class="form-control w-75" name="nom" type="text" v-model="nom" placeholder="Rechercher un étudiant..." required/>
            <input id="boutonRecherche" type="submit" value="Rechercher" />
        </div>
        
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

    #search{
  background-color: #A3B4C859;
  border-radius: 10px;
  border: 0px;
  padding-top: 5px;
  padding-bottom: 5px;
  font-family: 'Bebas Neue', sans-serif;
    }

    .formRecherche{
        display: flex;
        justify-content: space-between;
        font-family: 'Bebas Neue', sans-serif;
    }

    #boutonRecherche{
        border-radius: 10px;
        
    }

</style>