<template>
<div class="h-100 container ">
    <div class="row h-25 align-items-center">
        <h1 class="text-center">DOCUMENTS ADMINISTRATIFS</h1>
    </div>
    <div id="docAdmin" class="overflow-auto mb-0 d-flex flex-column justify-content-around  ">
        <div class="col-12 m-auto">
            <div class="row justify-content-around">    
                <Carte v-bind:nom="'DOSSIER DE CANDIDATURE 2022/2023'" v-bind:chemin="doc"/>
                <Carte v-bind:nom="'CONTRAT ETUDE 2022'" v-bind:chemin="doc"/>
                <Carte v-bind:nom="'LETTRE DE RESIDENT'" v-bind:chemin="doc"/>
            </div>
        </div>
    </div>
</div>
</template>

<script setup>
import Carte from "../../../components/CartePage.vue"
import doc from "./img/img_docsadmin.png"
</script>

<style>

</style>