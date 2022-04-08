<template>
  <div class="h-100 container ">
  <div class="row h-25  align-items-center">

    <div class="col-4 h-50  d-flex flex-column justify-content-around align-items-left">
      <input type="text" placeholder="RECHERCHER UNE DESTINATION..." class="w-100 inputFiltre">
      <div class="filtreTab w-100 d-flex  ">
          <a type="button" class="btn flex-fill text-center "> PAYS</a>
          <a type="button" class="btn flex-fill border"> TYPE MOBILITE</a>
          <a type="button" class="btn flex-fill border"> STATUT CONTRAT</a>
      </div>
    </div>

    <h1 class="col-4 text-center">GESTION DES DESTINATIONS</h1>

    <div class="col-4 text-right">
        <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#ajout">
            Ajouter une destination
        </button>
    </div>

  </div>
  <TableDestinations/>
  </div>
</template>

<script setup>


import TableDestinations from './tableDestinations/TableDestinations.vue'

</script>

<style>
</style>