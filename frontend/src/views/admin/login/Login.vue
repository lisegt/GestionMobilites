<template>
<div class="main">
    <form @submit.prevent="signin">
        <div>
            <label>Nom d'utilisateur</label>
            <input type="text" v-model="username"/>
        </div>
        <div>
            <label>Mot de passe</label>
            <input type="password" v-model="password"/>
        </div>
        <input class="btn_submit" type="submit" value="Login"/>
    </form>
</div>
</template>

<script setup>
    import {ref} from 'vue'
    import {useRouter} from 'vue-router'
    import { inject } from 'vue'

    import { createToast } from 'mosha-vue-toastify';
    import 'mosha-vue-toastify/dist/style.css'

    /*Pour gérer l'affichage du header*/
    const { header, headerVisible } = inject('header')

    /*Pour gérer le pseudo de l'utilisateur dans le header*/
    const {userInfos, setUserInfos} = inject('userInfos')

    const router = useRouter()

    let username = ref('')
    let password = ref('')

    /**
     * Fonction permettant de s'authentifier
     */
    function signin(){
        let url = '/api/auth/signin'
        let myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
        let body = JSON.stringify({username:username._value, password:password._value})
        fetch(url, {method: 'POST', headers: myHeaders, body: body})
        .then((response)=>{
            if(response.status != 200){
                toastDanger("Mot de passe ou nom d'utilisateur incorrect")
            }
            return response.json()
        })
        .then((dataJson)=>{
            localStorage.setItem('jwt','Bearer '+dataJson.accessToken)
            localStorage.setItem('userInfos',dataJson.username)
            setUserInfos(dataJson.username)
        })
        .then(()=>{
            headerVisible()
            router.push({name: 'Accueil'})
        })
        .catch((error)=>{
            console.log(error)
        })
    }

  /**
   * Fonction pour affichage de toast
   */
  function toastSuccess (message) {
      createToast(message, {type: 'success'})
  }

  function toastDanger (title, message) {
      createToast({ title: title, description: message}, {type: 'danger'})
  }

</script>

<style scoped>
    .main{
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 70vh;
    }

    form{
        display: flex;
        flex-direction: column;
        padding: 20px 30px;
        border: 2px solid #022e51;
    }

    form div{
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        margin-bottom: 10px;
    }

    .btn_submit{
        background-color: #b74803;
        color: white;
        font-weight: bold;
        border: none;
        padding: 5px;
        border-radius : 10px;
        margin: 0 20px;
    }

</style>
