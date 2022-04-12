<template>
<div class="  container table-responsive h-50 ">
  <table class="table table-striped  table-bordered ">
   <thead>
    <tr>
    <th class="text-center ">NOM </th>
    <th class="text-center ">PRENOM</th>
    <th class="text-center ">PROMOTION </th>
    <th class="text-center ">TYPE </th>
    <th class="text-center ">DESTINATION</th>
    <th class="text-center ">DEPART</th>
    <th class="text-center ">PERIODE</th>
    <th class="text-center">DUREE EN MOIS</th>
    <th class="text-center ">ETAT</th>
    <th class="text-center ">ACTIONS</th>
    </tr>
  </thead>
  <tbody >
    <tr v-for="(mobilite,index) of mobilites" :key=index>
    <td class="text-center ">{{mobilite[1].nom}}</td>
    <td class="text-center ">{{mobilite[1].prenom}}</td>
    <td class="text-center ">{{mobilite[1].promo}}</td>
    <td class="text-center ">{{mobilite[2].typeMobilite}}</td>
    <td style="text-center">{{mobilite[2].nomEtablissementAccueil}}</td>
    <td class="text-center ">{{mobilite[0].dateDepart}}</td>
    <td class="text-center">{{mobilite[0].periode}}</td>
    <td class="text-center">{{mobilite[0].dureeEnMois}}</td>
    <td class="text-center">{{mobilite[3]}}</td>
    <td class="text-center"> 
        <div class="text-center d-flex flex-row w-75 justify-content-around ">
           
        <a @click="$emit('set',mobilite)" data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn" type="button" > <img v-bind:src="addDocument" alt="edit"></a>
        
        <a @click="$emit('delete',mobilite[0].id)" class="btn" type="button"> <img v-bind:src="poubelle" alt="trash"></a>
        </div> 
    </td>
    </tr>
    
    
  </tbody>
</table>
<!-- Button trigger modal -->


<!-- Modal -->

</div>
</template>

<script setup>
   
    import poubelle from '../../../../img/poubelle.png'
    import addDocument from '../../../../img/ajouterUnDocument.png'
    
    defineProps(['mobilites'])
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