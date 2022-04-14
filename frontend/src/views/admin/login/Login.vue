<template>
    <h1>Login</h1>
    <form @submit.prevent="signin">
        <label>Username</label>
        <input type="text" v-model="username"/>
        <label>Password</label>
        <input type="password" v-model="password"/>
        <input type="submit" value="signin"/>
    </form>
</template>

<script setup>
    import {ref} from 'vue'
    import {useRouter} from 'vue-router'

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
            router.push({name: 'Accueil'})
        })
        .catch((error)=>{
            console.log(error)
        })
    }

    

</script>

<style scoped>

</style>
