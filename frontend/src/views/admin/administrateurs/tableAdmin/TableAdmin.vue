<template>
  <div class="mt-3">
      <table class="table table-striped table-bordered">
          <thead>
            <tr>
                <th class="text-center align-middle">Adresse mail</th>
                <th class="text-center align-middle">Actions</th>
            </tr>
            </thead>
            <tbody>
                <tr v-for="admin in admins">
                    <td class="text-center align-middle">{{admin.email}}</td>
                    <td class="text-center align-middle" v-if="admin.email != 'admin'"><a @click="$emit('deleteAdmin', admin.id)"><img :src="Poubelle" alt="à la poubelle l'admin"/></a></td>
                </tr>
            </tbody>
      </table>
  </div>
</template>

<script setup>
    import Poubelle from '../../../../img/poubelle.png'

    defineProps(['admins'])
</script>

<style>

</style>