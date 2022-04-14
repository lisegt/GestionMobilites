<template>
<div class="container table-responsive h-50 ">
  <table class="table table-striped  table-bordered ">
   <thead>
    <tr>
    <th class="text-center ">NOM ETABLISSEMENT </th>
    <th class="text-center ">VILLE </th>
    <th class="text-center ">PAYS </th>
    <th class="text-center ">TYPE MOBILITE </th>
    <th class="text-center ">NB PLACES </th>
    <th class="text-center ">STATUT CONTRAT </th>
    <th class="text-center ">ACTIONS</th>
    </tr>
  </thead>
  <tbody >
    <tr v-for="(destination,index) of destinations" :key=index >
    <td class="text-center ">{{destination[0].nomEtablissementAccueil}}</td>
    <td class="text-center ">{{destination[0].ville}}</td>
    <td class="text-center ">{{destination[0].pays}}</td>
    <td class="text-center ">{{destination[0].typeMobilite}}</td>
    <td style="d-flex ">
        <div class="text-center ">{{destination[0].nbPlaceAnnee}}/an </div>
        <div class="text-center ">{{destination[0].nbPlaceSemestre}}/semestre </div>
    </td>
    <td class="text-center ">{{destination[1]}}</td>
    <td class="text-center"> 
        <div class="text-center d-flex flex-row w-75 justify-content-around m-auto">
           
        <a @click="$emit('set',destination)" data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn" type="button" > <img v-bind:src="edit" alt="edit"></a>
        
        <a @click="$emit('delete',destination[0].id)" class="btn" type="button"> <img v-bind:src="poubelle" alt="trash"></a>
        </div> 
    </td>
    </tr>
    
    
  </tbody>
</table>
<!-- Button trigger modal -->


<!-- Modal -->
<FormModifDestination @updateImage="updateImage" @updateDestination="updateDestination"/>
</div>
</template>

<script setup>

    import {defineEmits} from 'vue'
   
    import poubelle from '../../../../img/poubelle.png'
    import edit from '../../../../img/edit.png'
    import FormModifDestination from '../formModifDestination/FormModifDestination.vue'

    defineProps(['destinations'])
    const emits = defineEmits(['updateImage','updateDestination'])
    function updateImage(event){
        emits('updateImage',event)
    }

    function updateDestination(event){
        emits('updateDestination',event)

    }
</script>

<style>

    .styleTab{
        border-radius: 1000px;
        border: 5px ridge #022e51;
    }
    thead{
        font-family: 'Bebas Neue', sans-serif;
        color: white;
        background-color: #022e51;
    }
</style>