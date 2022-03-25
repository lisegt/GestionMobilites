<template>
  <div class="container-fluid mb-0 border">
    <HeaderAdmin/>
    <Accueil />
    <Footer class="footer" />
  </div>
</template>

<style>



</style>

<script setup>
import HeaderAdmin from './components/HeaderAdmin.vue';
import Accueil from './views/admin/Accueil/Accueil.vue';
import Footer from './components/Footer.vue';
</script>
