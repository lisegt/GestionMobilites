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
            <tr v-for="(document) in listeDocuments" :key=[document.id] >
            <td class="text-center ">{{document.intitule}}</td>
            <td class="text-center ">{{document.description}}</td>
            <td class="text-center"> 
                <div class="text-center d-flex flex-row w-75 justify-content-around ">
                <a data-bs-toggle="modal" data-bs-target="#modif" class="btn" type="button" > <img v-bind:src="edit" alt="edit"></a>
                <FormModifDocsAdmin v-bind:doc="document"/>
                <a @click="deleteDocument(document.id)" class="btn" type="button"> <img v-bind:src="poubelle" alt="trash"></a>
                </div> 
            </td>
            </tr>
        </tbody>
    </table>
</div>
</template>

<script setup>
    import FormModifDocsAdmin from '../../docsAdmin/formModifDocsAdmin/FormModifDocsAdmin.vue'
    import edit from '../../../../img/edit2.png'
    import poubelle from '../../../../img/poubelle.png'

    import { onMounted, onUpdated } from "vue";
    import { reactive } from 'vue';
    //Liste de documents
    const listeDocuments = reactive([]);

    onMounted(() => {
    getDoc()
    });

    /**
     * 
     * @param
     * @return
     * Fonction qui remplit la liste listeDocuments
     */
    function getDoc(){
        const fetchOptions = {method:"GET"};
        fetch("http://localhost:8989/api/documents/",fetchOptions)
        .then((response) => {return response.json()})
        .then((dataJSON) => {
        listeDocuments.splice(0,listeDocuments.length)
        dataJSON._embedded.documents.forEach((d)=>listeDocuments.push(d))
        console.log(listeDocuments)
        })
        .catch((error) => console.log(error));
    }

    function deleteDocument(id){
        let url = `http://localhost:8989/api/documents/`
        
        let myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
        const fetchOptions = {method: "DELETE", headers: myHeaders};
        fetch(url+`${id}`,fetchOptions)
        .then(()=>{getDoc()})
        .catch((error)=>{console.log(error)})
    }
</script>

<style>
th{
    text-transform: uppercase;
}
</style>