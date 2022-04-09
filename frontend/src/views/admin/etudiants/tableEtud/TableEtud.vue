<template>
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
          
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
import { onMounted } from "@vue/runtime-core"
    defineProps(["etudiants"])

  
</script>

<style>

</style>