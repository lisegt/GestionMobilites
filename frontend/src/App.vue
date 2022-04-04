<template>
  <div class="container-fluid h-100">
    <HeaderAdmin/>
    <Accueil  />
    <Footer class="footer" />
  </div>
</template>

<style>



</style>

<script setup>
import HeaderAdmin from './components/HeaderAdmin.vue';
import Accueil from './views/admin/accueil/Accueil.vue';
import Footer from './components/Footer.vue';
</script>
