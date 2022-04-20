<template>
  <div>
      <table>
          <thead>
            <tr>
                <th>Adresse mail</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
                <tr v-for="admin in admins">
                    <td>{{admin.email}}</td>
                    <td>Delete</td>
                </tr>
            </tbody>
      </table>
  </div>
</template>

<script setup>
    defineProps(['admins'])
</script>

<style>

</style>