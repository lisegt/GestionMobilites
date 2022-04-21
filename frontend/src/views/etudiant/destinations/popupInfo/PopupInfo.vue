<template>
<div class="modal fade h-100" id="exampleModalLabel" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg h-100">
    <div class="modal-content">
      <div class="modal-header border-0 row py-0">
        <div class="col-xl-1"></div>
        <div class="col-10 text-center mt-3">
            <h5 class="modal-title font-weight-bold justify-content-center" id="Label">{{destination.nomEtablissementAccueil}} <h6>{{destination.ville}}, {{destination.pays}}</h6></h5>
        </div>
        <button type="button" class="btn-close col-1 me-4" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body h-25 contenu">
          <div class="col text-center">
            <img id="popUpImg" v-bind:src="image" class="img" width="420" height="440" alt="image ">
          </div>
          <div class="col justify-content-center my-4">
            <div class="row ">
              <div class="titre col align-items-center">Type Mobilité :<span class="font-weight-bold col valeur">{{destination.typeMobilite}}</span></div>
            </div>
            <div class="row">
              <div class="col titre align-items-center">Nombre de places disponibles : <span class="font-weight-bold valeur">{{destination.nbPlaceAnnee}} place(s) par an / {{destination.nbPlaceSemestre}} place(s) par semestre</span></div>
            </div>
            <div class="row">
              <div class="col titre align-items-center">Retour(s) d'expérience :</div>
              <span>
                <ul class="list-group">
                    <li v-for="(mobilite,index) of mobilites" v-bind:key="index" class=" row font-weight-bold d-flex align-items-center justify-content-start">
              
                         <div class="col-5 pr-0  text-right">Document {{index +1}} : </div>
                          <div  class="col-6 text-left h-100   align-items-left ">
                              <a @click="download(mobilite)" id="btnDownLoad" type="button" class="text-center btn bordureBtn font-weight-bold" >
                              Cliquez pour télécharger
                              </a>
                          </div>
                    </li>
                </ul>
              </span>
            </div>
          </div>
      </div>
    </div>
  </div>
</div>
</template>

<script setup>
import {onMounted,defineProps,defineEmits} from 'vue'
import etude from '../img/etude.png'
import humanitaire from '../img/humanitaire.png'
import stage from '../img/stage.png'


const props=defineProps(['destination','image','mobilites'])

/**
 * @param mobilite
 * Fonction qui télécharge le fichier
 */
function download(mobilite){
  const linkSource = mobilite.retourExperience;
  let btn = document.getElementById("btnDownLoad")
  const fileName = `retour_experience_${props.destination.nomEtablissementAccueil}.pdf`
  btn.href = linkSource;
  btn.download = fileName;
  btn.click();
}
</script>

<style scoped>
#Label, .titre{
    color: black;
    font-family: "Bebas Neue";
    font-size: 30px;
}
h6{
    font-family: "Montserrat";
    font-size: 24px;
}
.valeur{
    font-family: "Montserrat";
    font-size: 20px;
}
img{
  width: 80%;
  height: auto;
}
@media all and (orientation: portrait) {
  #Label, .titre{
    font-size: 24px;
}
h6{
    font-size: 18px;
}
}
</style>