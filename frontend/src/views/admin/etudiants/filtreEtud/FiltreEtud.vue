<template>
  <div style="display:flex; justify-content: space-between;">
        <div>
            <select>
                <option>Promotion</option>
                <option v-for="promo of data.promotions" :value="promo">
                    {{promo}}
                </option>
            </select>
        </div>
        <div>
            <select id="etat_mobilite" @change="searchByEtatMobilite">
                <option value="tous">Etat mobilité</option>
                <option value="val">Validée</option>
                <option value="cours">En cours</option>
                <option value="nVal">Non validée</option>
            </select>
        </div>
        <div>
            <select>
                <option>Type mobilité</option>
                <option>Etude</option>
                <option>Stage</option>
                <option>Humanitaire</option>
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

<style>

</style>