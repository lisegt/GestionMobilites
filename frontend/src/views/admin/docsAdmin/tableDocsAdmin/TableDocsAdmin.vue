<template>
<div class="container table-responsive">
    <table class="table table-striped mh-75  table-bordered overflow-scroll ">
        <thead>
            <tr>
                <th class="text-center ">Nom du document</th>
                <th class="text-center ">Description</th>
                <th class="text-center ">Actions</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(document) in documents" :key=[document.id] >
            <td class="text-center ">{{document.intitule}}</td>
            <td class="text-center ">{{document.description}}</td>
            <td class="text-center"> 
                <div class="text-center d-flex flex-row w-75 justify-content-around ">
                <a @click="$emit('update',document)" data-bs-toggle="modal" data-bs-target="#modif" class="btn" type="button" > <img v-bind:src="edit" alt="edit"></a>
                <a @click="$emit('delete',document.id)" class="btn" type="button"> <img v-bind:src="poubelle" alt="trash"></a>
                </div> 
            </td>
            </tr>
        </tbody>
    </table>

    <!-- Modal -->
    <FormModifDocsAdmin @updateFile="updateFile" @updateDoc="updateDoc"/>
</div>
</template>

<script setup>
    import FormModifDocsAdmin from '../../docsAdmin/formModifDocsAdmin/FormModifDocsAdmin.vue'
    import edit from '../../../../img/edit2.png'
    import poubelle from '../../../../img/poubelle.png'
    import { defineEmits} from 'vue'
import { emit } from 'process';


    defineProps(['documents'])
    const emits = defineEmits(['updateFile','updateDoc'])
    function updateFile(event){
            emits('updateFile',event)

    }

    function updateDoc(event){
        emits('updateDoc',event)

    }
</script>

<style>
th{
    text-transform: uppercase;
}
</style>