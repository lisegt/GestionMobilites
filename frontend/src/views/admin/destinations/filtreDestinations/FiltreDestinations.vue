<template>

        <button aria-expanded="false" type="button" id="dropdownPays" data-toggle="dropdown" class="btn flex-fill dropdown-toggle">PAYS</button>
            <ul class="dropdown-menu" aria-labelledby="dropdownPays" id="paysSelector">
                <li v-for="pays in listePays" :key="pays">{{pays}}</li>
            </ul>
        <button aria-expanded="false" type="button" id="dropdownType" data-toggle="dropdown" class="btn flex-fill dropdown-toggle">TYPE MOBILITE</button>
            <ul class="dropdown-menu" aria-labelledby="dropdownType" id="typeSelector">
                <li v-for="destination in destinations" :key="destination[0].typeMobilite" :id="destination[0].typeMobilite">{{ destination[0].typeMobilite }}</li>
            </ul>
</template>

<script setup>

import { reactive, onMounted } from 'vue'
defineProps(['destinations'])

const url = "http://localhost:8989/destinations/pays"
const listePays = reactive([]);

onMounted(()=>{
        recupererTousLesPays()
    })

//fonction qui permet de récupérer les pays de toutes les destinations à partir de l'API
function recupererTousLesPays(){

    const fetchOptions = { method: "GET" }; //on utilise l'opération GET car on veut récupérer le pays des destinations
    fetch(url, fetchOptions)
        .then((response) => { return response.json(); })
        .then((dataJSON) => {
            listePays.splice(0, listePays.length) //on vide la liste avant de la reremplir
            dataJSON.forEach((pays) => {
            listePays.push(pays) })

            console.log(dataJSON)
            console.log('test')
        })
        .catch((error) => console.log(error));
}

</script>

<style>

    .dropdown-menu li:hover {
        background-color: #b74803;
        color: #f3f3f3;
        cursor: pointer;
    }

</style>