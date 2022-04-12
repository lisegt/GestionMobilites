<template>
        <div class="filtresEtudiants w-100">
        <div >
            <select id="paysSelector" class="form-select inputFiltre" @change="searchByPays">
                <option value="tous">PAYS</option>
                <option v-for="pays in listePays" :key="pays" :value="pays">{{pays}}</option>
            </select>
        </div>

        <div>
            <select id="type_mobilite" class="form-select inputFiltre" @change="searchByTypeMobilite">
                <option value="tous">TYPE MOBILITE</option>
                <option v-for="type in types" :key="type" :value="type">{{type}}</option>
            </select>
        </div>

        <div>
            <select id="statut-contrat" class="form-select inputFiltre" @change="searchByStatutContrat">
                <option value="tous">STATUT CONTRAT</option>
                <option v-for="contrat in contrats" :key="contrat" :value="contrat">{{contrat}}</option>
            </select>
        </div>
    </div>
</template>

<script setup>

import { reactive, onMounted, defineEmits } from 'vue'

const url = "http://localhost:8989/destinations/pays"
const listePays = reactive([]); // liste qui contient les différents pays des destinations
const types = reactive([]); // liste qui contient les différents types de mobilité des destinations
const contrats = reactive([]); // liste qui contient les différents statuts de contrat des destinations

//on definit les evenements
    const emit = defineEmits(['searchByPays', 'searchByTypeMobilite', 'searchByStatutContrat'])

onMounted(()=>{
        recupererTousLesPays()
        recupererTousTypesMobilite()
        recupererTousStatutsContrat()
    })

//fonction qui permet de récupérer les pays de toutes les destinations à partir de l'API
function recupererTousLesPays(){

    const fetchOptions = { method: "GET" }; //on utilise l'opération GET car on veut récupérer le pays des destinations
    fetch('/api/destinations/pays', fetchOptions)
        .then((response) => { return response.json(); })
        .then((dataJSON) => {
            listePays.splice(0, listePays.length) //on vide la liste avant de la reremplir
            dataJSON.forEach((pays) => {
            listePays.push(pays) })
        })
        .catch((error) => console.log(error));
}

//fonction qui permet de récupérer les types de mobilité de toutes les destinations à partir de l'API
function recupererTousTypesMobilite(){

    const fetchOptions = { method: "GET" }; //on utilise l'opération GET car on veut récupérer le pays des destinations
    fetch('/api/destinations/typesMobilite', fetchOptions)
        .then((response) => { return response.json(); })
        .then((dataJSON) => {
            types.splice(0, types.length) //on vide la liste avant de la reremplir
            dataJSON.forEach((type) => {
            types.push(type) 
            console.log(types)
            })
        })
        .catch((error) => console.log(error));
}

//fonction qui permet de récupérer les statuts des contrats de toutes les destinations à partir de l'API
function recupererTousStatutsContrat(){

    const fetchOptions = { method: "GET" }; //on utilise l'opération GET car on veut récupérer le pays des destinations
    fetch('/api/destinations/statutsContrat', fetchOptions)
        .then((response) => { return response.json(); })
        .then((dataJSON) => {
            contrats.splice(0, contrats.length) //on vide la liste avant de la reremplir
            dataJSON.forEach((contrat) => {
            contrats.push(contrat) 
            console.log(contrats)
            })
        })
        .catch((error) => console.log(error));
}

//Pour envoyer un evenement au parent en fonction du pays choisi
function searchByPays(event){
    emit('searchByPays', event.target.value)
}

//Pour envoyer un evenement au parent en fonction du type de mobilité
function searchByTypeMobilite(event){
    emit('searchByTypeMobilite', event.target.value)
}

//Pour envoyer un evenement au parent en fonction du statut du contrat
function searchByStatutContrat(event){
    emit('searchByStatutContrat', event.target.value)
}

</script>

<style>

    .dropdown-menu li:hover {
        background-color: #b74803;
        color: #f3f3f3;
        cursor: pointer;
    }

</style>