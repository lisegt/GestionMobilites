<template>
    <div class="filtresEtudiants w-100">
        <div >
            <select style="background-color:rgba(163, 180, 200, 0.35);"  id="promotionEtud" class="form-select inputFiltre">
                <option style="background-color: #A3B4C8;" value="tous">Promotion</option>
                <option v-for="promo of data.promotions" :value="promo" :key="promo" class="text-center">{{promo}}</option>
            </select>
        </div>

        <div>
            <select style="background-color:rgba(163, 180, 200, 0.35);"  id="etat_mobilite" class="form-select inputFiltre" @change="searchByEtatMobilite">
                <option style="background-color: #A3B4C8;" value="tous">Etat mobilité</option>
                <option style="background-color: #A3B4C8;" value="val">Validée</option>
                <option style="background-color: #A3B4C8;" value="cours">En cours</option>
                <option style="background-color: #A3B4C8;" value="nVal">Non validée</option>
            </select>
        </div>

        <div>
            <select style="background-color:rgba(163, 180, 200, 0.35);"  id="type_mobilite" class="form-select inputFiltre" @change="searchByEtatMobilite">
                <option style="background-color: #A3B4C8;" value="tous">Type mobilité</option>
                <option style="background-color: #A3B4C8;" value="val">Etude</option>
                <option style="background-color: #A3B4C8;" value="cours">Stage</option>
                <option style="background-color: #A3B4C8;" value="nVal">Humanitaire</option>
            </select>
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
}

select{
    font-family: 'Bebas Neue', sans-serif;
}

</style>