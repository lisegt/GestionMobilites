<template>
  <table class="table table-striped">
   <thead>
    <tr>
    <th class="text-center ">NOM ETABLISSEMENT </th>
    <th class="text-center ">VILLE </th>
    <th class="text-center ">PAYS </th>
    <th class="text-center ">TYPE MOBILITE </th>
    <th class="text-center ">NB PLACES </th>
    <th class="text-center ">STATUT CONTRAT </th>
    <th class="text-center ">ACTIONS</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="(destination,index) in listeDestinations" :key=index :produit=produit   >
    <td class="text-center ">{{destination[0].nomEtablissementAccueil}}</td>
    <td class="text-center ">{{destination[1].nomVille}}</td>
    <td class="text-center ">{{destination[1].nomPays}}</td>
    <td class="text-center ">{{destination[0].typeMobilite}}</td>
    <td style="d-flex ">
        <div class="text-center ">{{destination[0].nbPlaceAnnee}}/an </div>
        <div class="text-center ">{{destination[0].nbPlaceSemestre}}/semestre </div>
    </td>
    <td class="text-center ">{{destination[2]}}</td>
    <td class="text-center"> 
        <div class="text-center d-flex flex-row w-75 justify-content-around ">
        <a @click="deleteDestination(destination[0].id)" class="btn" type="btn" > <img v-bind:src="edit" alt="edit"></a>
        
        <a @click="deleteDestination(destination[0].id)" class="btn" type="btn"> <img v-bind:src="poubelle" alt="trash"></a>
        </div> 
    </td>
    </tr>
    
    
  </tbody>
</table>
</template>

<script setup>
    import {reactive, onMounted} from 'vue'
    import poubelle from '../../../../img/poubelle.png'
    import edit from '../../../../img/edit.png'

    const listeDestinations =  reactive([])
    function dateDiff(date1, date2){
                var diff = {}                           // Initialisation du retour
                var tmp = date2 - date1;
            
                tmp = Math.floor(tmp/1000);             // Nombre de secondes entre les 2 dates
                diff.sec = tmp % 60;                    // Extraction du nombre de secondes
            
                tmp = Math.floor((tmp-diff.sec)/60);    // Nombre de minutes (partie entière)
                diff.min = tmp % 60;                    // Extraction du nombre de minutes
            
                tmp = Math.floor((tmp-diff.min)/60);    // Nombre d'heures (entières)
                diff.hour = tmp % 24;                   // Extraction du nombre d'heures
                
                tmp = Math.floor((tmp-diff.hour)/24);   // Nombre de jours restants
                diff.day = tmp;
                
                return diff;
    }
    function getDestinations(){
        
        listeDestinations.splice(0,listeDestinations.length)         //On vide la liste des destinations avant de la remplir afin d'éviter les doublons
    
        let url = `http://localhost:8989/api/destinations`
        fetch(url)
        .then((res)=>{return res.json()})
        .then((json)=>{
            let isValide;
            let date;
            let actuelDate = new Date();
            console.log(actuelDate)
            for(let d of json._embedded.destinations){

                 fetch(d._links.localisation.href)
                .then((res)=>{return(res.json())})
                .then((json)=>{
                    date=new Date(d.dateFinDeContratIsis)
                    dateDiff(actuelDate,date).day
                    if(dateDiff(actuelDate,date).day<385){
                                isValide = "Bientôt Expiré"
                    }
                    else{

                        isValide = "Valide"
                    }
                    listeDestinations.push([d,json,isValide])
                    
                    })

            }
            console.log(listeDestinations)
        })
    }

    function deleteDestination(id){
        console.log(id)
        let myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        const fetchOptions = {
                method: "DELETE",
                headers: myHeaders
                };
        let url = `http://localhost:8989/api/destinations/${id}`
        fetch(url,fetchOptions)
        .then((res)=>{getDestinations()})
        .catch((err)=>{console.log("message d'erreur : ",err)})
    }
    
    onMounted(()=>{
        getDestinations()
    })
</script>

<style>

</style>