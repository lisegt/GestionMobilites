<template>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  
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
import Accueil from './views/admin/Accueil/Accueil.vue';
import Footer from './components/Footer.vue';
</script>
