<template>
    <div class="filtresEtudiants w-100 justify-content-around">
        <div class="dropdown">
            <button class="btn dropdown-toggle" type="button" id="dropdownPromotion" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                PROMOTION
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownPromotion">
                <li v-for="promo of data.promotions" :value="promo" :key="promo" class="text-center">{{promo}}</li>
            </ul>
        </div>
        <div class="dropdown">
            <select style="background-color:rgba(163, 180, 200, 0.35);"  id="etat_mobilite" class="form-select inputFiltre" @change="searchByEtatMobilite">
                <option style="background-color: #A3B4C8;" value="tous">Etat mobilité</option>
                <option style="background-color: #A3B4C8;" value="val">Validée</option>
                <option style="background-color: #A3B4C8;" value="cours">En cours</option>
                <option style="background-color: #A3B4C8;" value="nVal">Non validée</option>
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
    const emit = defineEmits(['searchByEtatMobilite'])

    //liste des promotions
    let data = reactive(
        {
            promotions:[]
        }
    )

    //Pour récupérer toutes les promotions existantes
    function getPromotions(){
        return [2020, 2021, 2022, 2023, 2024]
    }

    onMounted(()=>{
        data.promotions = getPromotions()
    })

    //Pour envoyer un evenement au parent en fonction de l'etat de la mobilité choisie
    function searchByEtatMobilite(event){
        emit('searchByEtatMobilite', event.target.value)
    }


</script>

<style scoped>

.filtresEtudiants{
    font-family: 'Bebas Neue', sans-serif;
    display: flex;
}

</style>