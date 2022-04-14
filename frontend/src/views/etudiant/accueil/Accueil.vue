<template>
<div id="accueil" class="overflow-auto mb-0 d-flex flex-column justify-content-around  ">
    <div class="col-12 col-md-6 m-auto hauteur">
        <Carousel/>
    </div>
    <div class="col-10 m-auto justify-content-center">
        <div class="row justify-content-center gx-5 py-5">    
            <Carte v-bind:nom="'DESTINATIONS'" v-bind:lien="'/etudiant/destinations'" v-bind:chemin="destinations"/>
            <Carte v-bind:nom="nomDoc" v-bind:lien="'/etudiant/documents'" v-bind:chemin="doc"/>
        </div>
        
    </div>
</div>
</template>

<script setup>
import Carte from "../../../components/CarteAccueilEtud.vue"
import Carousel from "../../../components/Carousel.vue"

import destinations from "../../admin/accueil/img/img_destinations.png" 
import doc from "../../admin/accueil/img/img_docsadmin.png"

let nomDoc= "DOCUMENTS ADMIN."
if(window.innerWidth < window.innerHeight  ){
    nomDoc="DOCUMENTS"
}
</script>

<style>
#accueil{
    height: 85%;
    overflow: auto;
}
.hauteur{
    height: 50vh;
}

@media all and (orientation: portrait) {
    .hauteur{
        height: 28vh;
    }  
}
</style>