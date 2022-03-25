<template>
    
<div class="col-8 col-md-2  py-3 bg-color d-flex flex-column justify-content-center align-items-center border-0 bg-">

    <img v-bind:src="chemin" alt="image ">
    <div class="h3"> {{nom}}</div>
    <!--<img src="../views/admin/Accueil/img/img_docsadmin.png" alt=""> -->
</div>

</template>

<script setup>
defineProps(["chemin","nom"])
</script>

<style>
.bg-color{
    background-color: #B74803;
    border-radius: 50px;
    color: #fefefe;
    font-family: "Bebas Neue";
}


</style>