<template>
<div class="container-fluid h-100 border border-2 border-red ">
<!-- 
<div id="nav">
      <router-link to="/">Home</router-link> |
      <router-view />
      
</div>
-->
<HeaderAdmin/>


<Carte v-bind:nom="'Destination'" v-bind:chemin="'/static/img/img_destinations.06e12228.png'"/>
</div>
    
</template>

<script setup>

import HeaderAdmin from "../../../components/HeaderAdmin.vue"
import Carte from "../../../components/CarteAccueil.vue"


</script>