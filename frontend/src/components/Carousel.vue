<template>
<div class="col h-100">
 <div id="carouselExampleSlidesOnly" class="carousel slide h-100 " data-bs-ride="carousel">
  <div id="carouselImages" class="carousel-inner round h-100 ">
    <div id="blockImageDefaut" class="carousel-item active  d-block">
      <img id="imageDefaut" src="../img/image-defaut.jpg" class="d-block  h-100 w-100" alt="...">
    </div>
    
  </div>
  <button id="btnPrev" class="carousel-control-prev" type="button" data-bs-target="#carouselExampleSlidesOnly" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button id="btnNext" class="carousel-control-next" type="button" data-bs-target="#carouselExampleSlidesOnly" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>

</div>
</template>
<script setup>
import { when } from 'q';
import {reactive, onMounted} from 'vue'
import imageDefault from '../img/image-defaut.jpg'

const images = reactive([])

/**
 * @param base64img
 * @param callback
 *Conversion de l'image base64 en imge
**/
function Base64ToImage(base64img, callback) {
  var img = new Image();
  img.onload = function() {
      callback(img);
  };
  img.src = base64img;
}

/**
 * Lorsqu'on crée le composant Carousel, on exécute la fonction suivante
 */
onMounted(()=>{
  fetch('http://localhost:8989/api/destinations')
  .then((res)=>{
    return res.json()
  })
  .then((json)=>{
    let first=true
    for(let d of json._embedded.destinations){
      Base64ToImage(d.image, function(img) {
        document.getElementById("carouselImages").appendChild(document.createElement("div"))
        document.getElementById("carouselImages").lastChild.id=`img${d.id}`
        document.getElementById(`img${d.id}`).classList.add('carousel-item')
        document.getElementById(`img${d.id}`).classList.add('h-100')
        if(first){
          document.getElementById(`img${d.id}`).classList.add('active')
          first=false
        }
        img.classList.add('d-block')
        img.classList.add('w-100')
        img.classList.add('h-100')
        img.classList.add('round')
        document.getElementById(`img${d.id}`).appendChild(img)
        images.push(img)
        
        if(images.length>0 && document.getElementById("blockImageDefaut")){
          let block = document.getElementById("blockImageDefaut")
          let image = document.getElementById("imageDefaut")
          image.remove()
          block.remove()       
        }
      });
    }
  })
})
</script>

<style>
.round{
    
    border-radius: 40px;
}

</style>