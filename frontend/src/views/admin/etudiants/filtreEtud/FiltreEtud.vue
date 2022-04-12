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

<style>

.filtresEtudiants{
    font-family: 'Bebas Neue', sans-serif;
}

select{
    font-family: 'Bebas Neue', sans-serif;
}

</style>