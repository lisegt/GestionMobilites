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
        <input type="submit" value="Login"/>
    </form>
</div>
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
