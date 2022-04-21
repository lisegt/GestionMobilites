<template>
<div id="accueil" class="overflow-auto mb-0 d-flex flex-column align-items-center justify-content-center ">
    <div class="col-12 hauteur col-md-6 m-auto">
        <Carousel/>
    </div>
    <div class="col-12 col-md-6 m-auto">
        <div class="row d-flex justify-content-center  pb-5 pt-0 pb-md-5 gy-xl-0 gx-md-4">    
            <Carte v-bind:nom="'DESTINATIONS'" v-bind:lien="'/etudiant/destinations'" v-bind:chemin="destinations"/>
            <Carte v-bind:nom="'DOCUMENTS'" v-bind:lien="'/etudiant/documents'" v-bind:chemin="doc"/>
        </div>
    </div>
</div>
</template>

<script setup>
import Carte from "../../../components/CarteAccueilEtud.vue"
import Carousel from "../../../components/Carousel.vue"

import destinations from "../../admin/accueil/img/img_destinations.png" 
import doc from "../../admin/accueil/img/img_docsadmin.png"

/**
 * Activer les items de la navbar selon la page consultée
 * On retire l'attribut de tous les items de la nav et on le rajoute à l'item de la page concernée
**/
let listeNav = ["accueilNav","destinationsNav","docNav"]
for(let l of listeNav){
document.getElementById(l).classList.remove("active")
}
document.getElementById("accueilNav").classList.add("active")

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