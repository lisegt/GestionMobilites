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
            <button class="btn dropdown-toggle" type="button" id="dropdownEtatMobilite" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                ETAT MOBILITE
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownEtatMobilite">
                <li class="text-center">Validée</li>
                <li class="text-center">En cours</li>
                <li class="text-center">Non Validée</li>
            </ul>
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

    let data = reactive(
        {
            promotions:[]
        }
    )
    //liste des promotions

    //Pour récupérer toutes les promotions existantes
    function getPromotions(){
        return [2020, 2021, 2022, 2023, 2024]
    }

    onMounted(()=>{
        data.promotions = getPromotions()
    })
    
</script>

<style scoped>

.filtresEtudiants{
    font-family: 'Bebas Neue', sans-serif;
    display: flex;
    justify-content: space-around;
}

</style>