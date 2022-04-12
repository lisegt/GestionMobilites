<template>
    <div class="filtresEtudiants w-100 justify-content-around">
        <div id="promotion" class="dropdown">
            <select class="form-select inputFiltre" @change="searchByPromo">
                <option :value="0">PROMOTION</option>
                <option v-for="promo of data.promotions" :value="promo" :key="promo">{{promo}}</option>
            </select>
        </div>
        <div class="dropdown">
            <select id="etat_mobilite" class="form-select inputFiltre" @change="searchByEtatMobilite">
                <option value="tous">Etat mobilité</option>
                <option value="val">Validée</option>
                <option value="cours">En cours</option>
                <option value="nVal">Non validée</option>
            </select>
        </div>

        <div class="dropdown">
            <button class="btn dropdown-toggle" type="button" id="dropdownTypeMobilite" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                TYPE MOBILITE
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownTypeMobilite">
                <li class="text-center">Etude</li>
                <li class="text-center">Stage</li>
                <li class="text-center">Humanitaire</li>
            </ul>
        </div>
    </div>
    

</template>

<script setup>
import { onMounted, reactive } from "@vue/runtime-core";
import {defineEmits} from 'vue'

    //on definit les evenements
    const emit = defineEmits(['searchByEtatMobilite', 'searchByPromo'])

    //liste des promotions
    let data = reactive(
        {
            promotions:[]
        }
    )

    //Pour récupérer toutes les promotions existantes
    function getPromotions(){
        fetch('/api/etudiant/promo', {method: 'GET'})
        .then((result)=>
            result.json()
        )
        .then((dataJson)=>{
            data.promotions = dataJson
        })
    }

    onMounted(()=>{
        getPromotions()
    })

    //Pour envoyer un evenement au parent en fonction de l'etat de la mobilité choisie
    function searchByEtatMobilite(event){
        emit('searchByEtatMobilite', event.target.value)
    }

    //Pour envoyer un evenement au parent en fonction de la promotion
    function searchByPromo(event){
        emit('searchByPromo', event.target.value)
    }


</script>

<style scoped>

.filtresEtudiants{
    font-family: 'Bebas Neue', sans-serif;
    display: flex;
}

</style>