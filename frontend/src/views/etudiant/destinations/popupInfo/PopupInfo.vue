<template>
<div class="modal fade h-100" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg h-100">
    <div class="modal-content">
      <div class="modal-header border-0 row py-0">
        <div class="col-1"></div>
        <div class="col-10 text-center mt-3">
            <h5 class="modal-title font-weight-bold justify-content-center" id="Label">{{destination.nomEtablissementAccueil}} <h6>{{destination.ville}}, {{destination.pays}}</h6></h5>
        </div>
        <button type="button" class="btn-close col-1 me-4" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body h-25 contenu">
          <div class="col text-center">
            <img v-bind:id="index" v-bind:src="img" class="img" width="170" height="190" alt="image ">
          </div>
          <div class="col justify-content-center my-4">
            <div class="row">
              <div class="titre col">Type Mobilité :<span class="col valeur">Mobilité {{destination.typeMobilite}}</span></div>
            </div>
            <div class="row">
              <div class="col titre">Nombre de places disponibles :<span class="col valeur">{{destination.nbPlaceAnnee}} place(s) par an / {{destination.nbPlaceSemestre}} place(s) par semestre</span></div>
            </div>
            <div class="row">
              <div class="col titre">Retour(s) d'expérience :</div>
              <span>
                <ul>
                    <li>
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
import {onMounted, ref} from 'vue'
import etude from '../img/etude.png'
import humanitaire from '../img/humanitaire.png'
import stage from '../img/stage.png'

const props = defineProps(['destination', 'index', 'img', 'type'])
let type = ref({})

/**
 * @param base64img
 * Fonction qui ajoute l'image
 * Prend en compte le fait si l'image existe ou non
 * Sinon image par défaut selon le type de mobilité
 */
function Base64ToImage(base64img) {
  if(props.img==""){
    
    if(props.type=="STAGE"){
      console.log(props.type)
      document.getElementById(`${props.index}`).src=stage
    }
    if(props.type=="ETUDE"){
      document.getElementById(`${props.index}`).src=etude
    }
    if(props.type=="HUMANITAIRE"){
      document.getElementById(`${props.index}`).src=humanitaire
    }
  }
  else{
      document.getElementById(`${props.index}`).src=props.img
  }
}

/**
 * Lorsqu'on crée le composant PopupInfo, on exécute la fonction Base64ToImage(props.img)
 */
onMounted(()=>{Base64ToImage(props.img)})
</script>

<style scoped>
#Label, .titre{
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
  height: auto;
}
</style>