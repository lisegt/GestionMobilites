<template>
<div class="container border border-2 border-red ">
<div id="nav">
      <router-link to="/">Home</router-link> |
      <router-view />
    </div>
</div>
    
</template>

<script setup>
import Carte from "../../components/CarteAccueil.vue"

</script>