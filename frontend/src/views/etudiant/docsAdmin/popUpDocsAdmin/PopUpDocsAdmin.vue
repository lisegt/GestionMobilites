<template>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content h-50">
      <div class="modal-header border-0 row">
          <div class="col-1"></div>
          <div class="modal-title text-center col-10 h3" >{{document.intitule}}</div>
          <button type="button" class="btn-close col-1 me-3" data-bs-dismiss="modal" aria-label="Close"></button>
        
      </div>
      <div class="modal-body h-25 justify-content-center">
          <div class="row h-50 overflow-y-auto">
              <div class="h4 text-left col-2">Description :</div>
              <div class="col-10 text-left h-100 overflow-auto" style="word-wrap:break-word;">{{document.description}}</div>
            
              
          </div>

          <div class="row mt-3 h-25 align-items-center justify-content-start">
              <div class="col-4 h4 text-left">Lien de telechargement : </div>
              <div  class="col-8 text-left h-100   align-items-center ">
                  <a @click="download" id="btnDownLoad" type="button" class="text-center btn bordureBtn font-weight-bold" >
                  Cliquez pour télécharger
                  </a>
              </div>
          </div>
      </div>
    </div>
  </div>
</div>
</template>

<script setup>


import {  defineEmits } from 'vue';

const emits = defineEmits(['updateImage','updateDestination'])
const props = defineProps(['document'])

console.log(props.document.fichier)

/**
 * @param event
 * Fonction qui met à jour l'image
 */
function changeImage(event){
  emits('updateImage', event )
}

/**
 * @param event
 * Fonction qui met à jour la destination
 */
function updateDestination(event){
  emits('updateDestination',event)
}

/**
 * Fonction qui télécharge le fichier
 */
function download(){
  const linkSource = `${props.document.fichier}`;
  let btn = document.getElementById("btnDownLoad")

  const fileName = `${props.document.intitule}.pdf`
  btn.href = linkSource;
  btn.download = fileName;
  btn.click();
}
</script>

<style scoped>
#exampleModal{
    border-radius: 10px;
    font-family: 'Montserrat', sans-serif;
}

.h3{
  color: black;
  font-family: 'Bebas Neue', sans-serif;
}


.h4{
  color: black;
  font-family: 'Bebas Neue', sans-serif;
}

.bordureBtn{
  border : 1px solid black;
}

</style>
