<template>
    
<div class="col-2 d-flex flex-column justify-content-center align-items-around">

    <img src="{{chemin}}" alt="image pour {{nom}}">
    <div class="h3"> {{nom}}</div>
</div>

</template>

<script setup>
defineProps(["chemin","nom"])

</script>