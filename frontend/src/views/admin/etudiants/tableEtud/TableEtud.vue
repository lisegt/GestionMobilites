<template>
  <div class="container table-responsive h-50" >
    <table class="table table-striped  table-bordered ">
        <thead>
          <tr>
            <th class="text-center">INE</th>
            <th class="text-center">Nom</th>
            <th class="text-center">Prenom</th>
            <th class="text-center">Promotion</th>
            <th class="text-center">Etat mobilité</th>
            <th class="text-center">Type mobilité</th>
            <th class="text-center">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="etud in etudiants">
            <td class="text-center">{{etud.numEtud}}</td>
            <td class="text-center">{{etud.nom}}</td>
            <td class="text-center">{{etud.prenom}}</td>
            <td class="text-center">{{etud.promo}}</td>
            <td class="text-center">null</td>
            <td class="text-center">null</td>
            <td class="text-center">
              <div class="text-center d-flex justify-content-around w-75 m-auto">
              <a @click="$emit('update', etud)" data-bs-toggle="modal" data-bs-target="#modif" class="btn" type="button" > <img v-bind:src="edit" alt="edit"></a>
              <a @click="$emit('delete',etud.id)" ><img :src="Poubelle" alt="à la poubelle l'etudiant"/></a>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
</template>

<script setup>
    import { onMounted } from "@vue/runtime-core"
    import Poubelle from '../../../../img/poubelle.png'
    import edit from '../../../../img/edit.png'
    defineProps(["etudiants"])

  
</script>

<style scoped>

  th{
    font-size: 1.2em;
  }

  td{
  }

</style>