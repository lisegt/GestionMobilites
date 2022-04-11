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
            <select>
                <option>Etat mobilité</option>
                <option>Validée</option>
                <option>En cours</option>
                <option>Non validée</option>
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

<style>

</style>