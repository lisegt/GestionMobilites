<template>
  <div class="container table-responsive h-50" >
    <table>
        <thead>
          <tr>
            <th>INE</th>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Promotion</th>
            <th>Etat mobilité</th>
            <th>Type mobilité</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="etud in etudiants">
            <td>{{etud.numEtud}}</td>
            <td>{{etud.nom}}</td>
            <td>{{etud.prenom}}</td>
            <td>{{etud.promo}}</td>
            <td>null</td>
            <td>null</td>
            <td>
              <a @click="$emit('delete',etud.id)" ><img :src="Poubelle" alt="à la poubelle l'etudiant"/></a>
              <a @click="$emit('update', etud)" data-bs-toggle="modal" data-bs-target="#modif" class="btn" type="button" > <img v-bind:src="edit" alt="edit"></a>
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

<style>

</style>