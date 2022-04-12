<template>
<div class="col h-100">
 <div id="carouselExampleSlidesOnly" class="carousel slide h-100 " data-bs-ride="carousel">
  <div id="carouselImages" class="carousel-inner h-100 ">
    
    
  </div>
</div>

</div>
</template>
<script setup>
import {reactive, onMounted} from 'vue'
 //image base64 en image 
    const images = reactive([])
    function Base64ToImage(base64img, callback) {
    var img = new Image();
    img.onload = function() {
        callback(img);
    };
    img.src = base64img;
    }
    
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
              console.log(img)
              images.push(img)
              /*
              var log = "w=" + img.width + " h=" + img.height;
              document.getElementById('log').value = log;*/

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