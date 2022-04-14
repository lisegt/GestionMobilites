<template>
  <form @submit.prevent="addAdmin">
      <label>Username</label>
      <input type="text" v-model="username"/>
      <label>Email</label>
      <input type="email" v-model="email"/>
      <label>Password</label>
      <input type="password" v-model="password"/>
      <input type="submit" value="ajouter"/>
  </form>
</template>

<script setup>
    import { ref } from "vue";

    const emit = defineEmits(['addAdmin'])

    let username = ref('')
    let email = ref('')
    let password = ref('')

    function addAdmin(){
        emit('addAdmin',username,email,password)
    }
</script>

<style scoped>

</style>