<template>
<div class="col-12 col-md-2 ">
<a type="button" class=" text-white py-3  mx-2  bg-color d-flex flex-column justify-content-center align-items-center border-0 ">

    <img v-bind:src="chemin" width="70" height="65" alt="image ">
    <div class="h4 mt-3"> {{nom}}</div>

</a>
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