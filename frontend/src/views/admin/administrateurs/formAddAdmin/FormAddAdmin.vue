<template>
<div class="modal fade" id="ajouter" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title font-weight-bold" id="Label">Ajouter un administrateur</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div id="form">
    <form @submit.prevent="addAdmin">
      <div class="form-group">
        <label class="font-weight-bold">Username</label>
        <input class="form-control" type="text" v-model="username"/>
      </div>
      <div class="form-group">
        <label class="font-weight-bold">Email</label>
        <input class="form-control" type="text" v-model="email"/>
      </div>
      <div class="form-group">
        <label class="font-weight-bold">Password</label>
        <input class="form-control" type="password" v-model="password"/>
      </div>
       <div class="modal-footer">
          <button type="button" class="btnBleu" data-bs-dismiss="modal">Annuler</button>
          <input type="submit" value="ajouter" class="btnOrange" data-bs-dismiss="modal"/>
       </div>
    </form>
  </div>
      </div> 
    </div>
  </div>
</div>
</template>

<script setup>
import { ref } from "vue";
const emit = defineEmits(['addAdmin'])

let username = ref('')
let email = ref('')
let password = ref('')

/**
* Fonction qui permet d'ajouter un administrateur
*/
function addAdmin(){
  emit('addAdmin',username,email,password)
  username.value = ''
  email.value = ''
  password.value = ''
}
</script>

<style scoped>
    h5{
    font-size: 30px;
    line-height: 36px;
    }
    label{
        font-size: 24px;
        line-height: 29px;
    }
    h5,label{
        text-transform: uppercase;
        color: #022E51;
        font-family: 'Bebas Neue';
        font-weight: 400;
        font-style: normal;
        text-align: center;
    }
    .form-control{
        background: rgba(163, 180, 200, 0.35);
        border-radius: 10px;
        font-family: 'Montserrat';
        font-weight: 400;
        font-size: 20px;
        color: #022E51;
    }
    #ajout{
        border-radius: 10px;
    }
</style>