<template>
  <div class="container-fluid">
    <HeaderAdmin/>
    <Accueil/>
  </div>
</template>

<style>

body {
  background: #f3f3f3;
}


</style>

<script setup>
import HeaderAdmin from './components/HeaderAdmin.vue';
import Accueil from './views/admin/Accueil/Accueil.vue';
</script>
