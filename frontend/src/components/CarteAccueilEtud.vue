<template>
<div class="col-6 col-xl-3 ">
    <a type="button" v-bind:href="lien"  class=" text-white py-3 paddingX px-md-0 mx-2 bg-color d-flex flex-column justify-content-center align-items-center border-0 ">
        <img v-bind:src="chemin" width="140" height="100" alt="image ">
        <div class="h4 mt-3"> {{nom}}</div>
    </a>
</div>
</template>

<script setup>
defineProps(["chemin","nom","lien"])
</script>

<style>
#carte{
    background-color: #B74803;
    border-radius: 50px;
    color: #F3F3F3;
    font-family: "Bebas Neue";
    font-size: 30px;
}
.h4{
    text-align: center;
    padding-left: 20px;
    padding-right: 20px;
}
</style>