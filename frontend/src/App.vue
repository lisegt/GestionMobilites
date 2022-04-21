<template>
  <!-- Authentification Branch -->
  <div class="container-fluid overflow-scroll h-100">
    <div v-if="header">
       <HeaderAdmin />
    </div>
    <div v-else >
      <HeaderEtudiant/>
    </div>
    <router-view />
    <Footer class="footer" />
  </div>
</template>

<script setup>
  import HeaderAdmin from './components/HeaderAdmin.vue';
  import HeaderEtudiant from './components/HeaderEtudiant.vue';
  import Accueil from './views/admin/accueil/Accueil.vue';
  import Footer from './components/Footer.vue';

  import { provide, ref } from 'vue'

  /*Pour gérer l'affichage du header*/
  const header = ref(localStorage.getItem('jwt'))

  function headerVisible() {
    header.value = localStorage.getItem('jwt')
  }

  function headerCache() {
    header.value = null
  }

  provide('header', {
    header,
    headerVisible,
    headerCache
  })

  /*Pour gérer l'affichage du nom de l'utilisateur dans le header */
  const userInfos = ref(localStorage.getItem('userInfos'))

  function setUserInfos(name){
    userInfos.value = name
  }

  provide('userInfos', {
    userInfos,
    setUserInfos,
  })

</script>


<style>
@font-face
{
    font-family: 'Bebas Neue';
    src: url('../public/polices/BebasNeue-Regular.eot');
    src: url('../public/polices/BebasNeue-Regular.woff') format('woff'),
         url('../public/polices/BebasNeue-Regular.ttf') format('truetype');
    font-weight: normal;
    font-style: normal;
}

html {
 
  margin: 0;
  height: 100%;
  width: 100%;

}

#app {
    background: #f3f3f3;
    font-family: 'Montserrat', sans-serif;
    margin: auto;
    padding: 0;
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    
  }

.footer{
    position: absolute;
    bottom: 0;
    width: 100%;
   
  }

  h1{
    font-family: 'Bebas Neue', sans-serif;
  }

  .btnOrange{
    background-color: #B74803;
    border-radius: 30px;
    font-family: 'Bebas Neue';
    text-transform: uppercase;
    color: white;
    font-size: 24px;
    border: 0px;
    padding: 10px;
}

.btnBleu{
    background-color: #022E51;
    border-radius: 30px;
    font-family: 'Bebas Neue';
    text-transform: uppercase;
    color: white;
    font-size: 24px;
    border: 0px;
    padding: 10px;
}

.inputFiltre{
  background-color: #A3B4C859;
  border-radius: 10px;
  border: 0px;
  padding-top: 5px;
  padding-bottom: 5px;
}

.filtreTab{
  background-color: #a3c8c459;
  border-radius: 10px;
  border: 0px;
}

.scrollTableau{
    height: 30em;
    overflow-y: scroll ;
  }

</style>


