<template>
    <div class="col-6 col-xl-3 ">
        <a type="button" id="carte" class=" py-3 d-flex flex-column justify-content-center align-items-center border-0 ">
            <img v-bind:src="chemin" width="176" height="165" alt="image ">
            <div class="h4 mt-3"> {{nom}}</div>
        </a>
    </div>
</template>

<script setup>
defineProps(["chemin","nom"])
</script>

<style>
#carte{
    background-color: #B74803;
    border-radius: 30px;
    color: #FFFFFF;
    font-family: "Bebas Neue";
    font-size: 24px;
    width: 360px;
    height: 247;
}
.h4{
    text-align: center;
    padding-left: 4px;
    padding-right: 4px;
}
</style>