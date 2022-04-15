<template>
<div class="h-100 container-fluid">
    <div class="row h-25 align-items-center">
        <h1 class="text-center">DÉCOUVREZ LES DESTINATIONS</h1>
    </div>
    <div class="row border g-5 h-50 overflow-auto">
        <Carte v-for="(destination,index) of listeDestinations" v-bind:index="destination.id"  v-bind:nom="destination.nomEtablissementAccueil" v-bind:ville="destination.ville" v-bind:pays="destination.pays" v-bind:type="destination.typeMobilite" v-bind:img="destination.image"/>
    </div>
</div>
</template>

<script setup>
import {reactive, onMounted,ref} from 'vue'
import Carte from "../../../components/CarteDestinationsEtud.vue"

const listeDestinations = reactive([])

const urlAllDestinations = '/api/destinations';

function dateDiff(date1, date2){
    var diff = {}                           // Initialisation du retour
    var tmp = date2 - date1;
    
    tmp = Math.floor(tmp/1000);             // Nombre de secondes entre les 2 dates
    diff.sec = tmp % 60;                    // Extraction du nombre de secondes

    tmp = Math.floor((tmp-diff.sec)/60);    // Nombre de minutes (partie entière)
    diff.min = tmp % 60;                    // Extraction du nombre de minutes

    tmp = Math.floor((tmp-diff.min)/60);    // Nombre d'heures (entières)
    diff.hour = tmp % 24;                   // Extraction du nombre d'heures
    
    tmp = Math.floor((tmp-diff.hour)/24);   // Nombre de jours restants
    diff.day = tmp;
    return diff;
}

function Base64ToImage(base64img, callback) {
    var img = new Image();
    img.onload = function() {
        callback(img);
    };
    img.src = base64img;
    }

function getDestinations(url){
    listeDestinations.splice(0,listeDestinations.length) //On vide la liste des destinations avant de la remplir afin d'éviter les doublons

    fetch(url)
    .then((res)=>{return res.json()})
    .then((json)=>{
      
        let date;
        let actuelDate = new Date();
    
        for(let d of json._embedded.destinations){
            date=new Date(d.dateFinDeContratIsis)
            
            if(dateDiff(actuelDate,date).day>0){
                listeDestinations.push(d)
            }
        }
    })
}

/**
 * lorsqu'on crée le composant Destinations, on exécute la fonction getDestinations(url) qui charge toutes les destinations de la BDD
 */
onMounted(()=>{ getDestinations(urlAllDestinations) })
</script>

<style>

</style>