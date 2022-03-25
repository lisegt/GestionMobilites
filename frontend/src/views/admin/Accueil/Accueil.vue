<template>
<div class="container-fluid h-100 border border-2 border-red ">
<!-- 
<div id="nav">
      <router-link to="/">Home</router-link> |
      <router-view />
      
</div>
-->
<HeaderAdmin/>


<Carte v-bind:nom="'Destination'" v-bind:chemin="'../views/admin/Accueil/img/img_destionations.png'"/>
</div>
    
</template>

<script setup>

import HeaderAdmin from "../../../components/HeaderAdmin.vue"
import Carte from "../../../components/CarteAccueil.vue"


</script>