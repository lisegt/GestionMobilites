<template>
<div class="main">
    <form @submit.prevent="signin">
        <div>
            <label>Username</label>
            <input type="text" v-model="username"/>
        </div>
        <div>
            <label>Password</label>
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

    const { header, headerVisible } = inject('header')

    const router = useRouter()

    let username = ref('')
    let password = ref('')

    function signin(){
        let url = '/api/auth/signin'
        let myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
        let body = JSON.stringify({username:username._value, password:password._value})
        fetch(url, {method: 'POST', headers: myHeaders, body: body})
        .then((response)=>{
            return response.json()
        })
        .then((dataJson)=>{
            localStorage.setItem('jwt','Bearer '+dataJson.accessToken)
        })
        .then(()=>{
            headerVisible()
            router.push({name: 'Accueil'})
        })
        .catch((error)=>{
            alert('mot de passe ou identifiant incorrect')
            console.log(error)
        })
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
        border: 2px solid #b74803;
        border-radius: 10px;
    }

    form div{
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        margin-bottom: 10px;
    }

    .btn_submit{
        background-color: #022e51;
        color: white;
        font-weight: bold;
        border: none;
        padding: 5px;
        border-radius : 10px;
        margin: 0 20px;
    }

</style>
