<template>
    
<div class="col-2  py-3 bg-color d-flex flex-column justify-content-center align-items-center border-0 bg-">

    <img v-bind:src="chemin" alt="image ">
    <div class="h3"> {{nom}}</div>
    
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