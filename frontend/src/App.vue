<template>

  <div class="container-fluid overflow-scroll h-100">
    <HeaderAdmin/>
    <router-view />
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
