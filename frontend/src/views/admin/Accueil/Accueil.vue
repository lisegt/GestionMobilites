<template>
<div class="container-fluid">
<!-- 
<div id="nav">
      <router-link to="/">Home</router-link> |
      <router-view />
      
</div>
-->

<Carte v-bind:nom="'Destination'" v-bind:chemin="'/static/img/img_destinations.06e12228.png'"/>
</div>
    
</template>

<script setup>

import HeaderAdmin from "../../../components/HeaderAdmin.vue"
import Carte from "../../../components/CarteAccueil.vue"


</script>