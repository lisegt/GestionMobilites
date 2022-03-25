<template>
<div>
<div class="row">

</div>
<div class="row border  justify-content-around g-5 gx-md-1">
     <!-- pas beau <Carousel/> -->
<div class="col-12 col-md-1"></div>
<Carte v-bind:nom="'ETUDIANTS'" v-bind:chemin="etudiants"/>
<Carte v-bind:nom="'DESTINATIONS'" v-bind:chemin="destinations"/>
<Carte v-bind:nom="'MOBILITES'" v-bind:chemin="mobilites"/>
<Carte v-bind:nom="'DOCUMENTS ADMIN.'" v-bind:chemin="doc"/>
<div class="col-12 col-md-1"></div>
</div>
</div>
    
</template>

<script setup>


import Carte from "../../../components/CarteAccueil.vue"
import Carousel from "../../../components/Carousel.vue"

import etudiants from "./img/etudiants.png" 
import destinations from "./img/img_destinations.png" 
import mobilites from "./img/img_mobilités.png" 
import doc from "./img/img_docsadmin.png" 






</script>