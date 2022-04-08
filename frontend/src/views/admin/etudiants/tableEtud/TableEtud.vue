<template>
<button value="test" @click="test"/>
  <table>
      <thead>
          <tr>
              <th>INE</th>
              <th>NOM</th>
              <th>PRENOM</th>
              <th>PROMOTION</th>
              <th>ETAT MOBILITE</th>
              <th>TYPE MOBILITE</th>
              <th>ACTIONS</th>
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
              <td>null</td>
          </tr>
      </tbody>
  </table>
</template>

<script setup>
    defineProps(["etudiants"])
    let test = () => {
        console.log(etudiants)
    }
</script>

<style>

</style>