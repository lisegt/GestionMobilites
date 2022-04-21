<template>
    <div class="col-12   col-xl-4">
        <a type="button" @click="oppenCard" data-bs-toggle="modal" data-bs-target="#exampleModalLabel" class="carte  px-md-0 w-100 m-0 bg-color d-flex flex-column pt-3 align-items-center border-0">
            <img v-bind:id="index" v-bind:src="img" class="img" width="140" height="135" alt="image ">
            <div  class="h4 mt-3 txt"> {{nom}} <br> <div id="lieu">{{ville}}, {{pays}}</div></div>
        </a>

    </div>
</template>

<script setup>
import {onMounted, defineEmits} from 'vue'

import Popup from "../views/etudiant/destinations/popupInfo/PopupInfo.vue"

import etude from '../views/etudiant/destinations/img/etude.png'
import humanitaire from '../views/etudiant/destinations/img/humanitaire.png'
import stage from '../views/etudiant/destinations/img/stage.png'


const props = defineProps(["img","nom","ville","pays","index","type","destination"])

const emits = defineEmits(['oppen'])
function oppenCard(){
    
    emits('oppen',props.destination)
    
}

function Base64ToImage() {
    if(props.img==""){
        console.log("on passe")
        if(props.type=="STAGE"){
            console.log("on passe 2")
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

onMounted(()=>{Base64ToImage(props.img)})
</script>

<style>
.img{
    width: 80%;
    border-radius: 30px;
}
.carte{
    background-color: #B74803;
    border-radius: 30px;
}
.txt{
    color: #FFFFFF;
    font-family: "Bebas Neue";
    font-size: 24px;
}
#lieu{
    color: #FFFFFF;
    font-family: "Montserrat";
    font-size: 20px;
}
</style>