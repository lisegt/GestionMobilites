<template>
  <div class="main">
    <form @submit.prevent="addAdmin">
      <div>
        <label>Username</label>
        <input type="text" v-model="username"/>
      </div>
      <div>
        <label>Email</label>
        <input type="email" v-model="email"/>
      </div>
      <div>
        <label>Password</label>
        <input type="password" v-model="password"/>
      </div>
      <input type="submit" value="ajouter"/>
    </form>
  </div>
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
    .main{
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 60vh;
    }

    form{
        display: flex;
        flex-direction: column;
    }

    form div{
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        margin-bottom: 10px;
    }
</style>