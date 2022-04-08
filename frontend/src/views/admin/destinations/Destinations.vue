<template>
  <div class="h-100 container ">
  <div class="row h-25  align-items-center">

    <div class="col-4 h-50  d-flex flex-column justify-content-around align-items-left">
      <input type="text" placeholder="RECHERCHER UNE DESTINATION..." class="w-100 inputFiltre">
      <div class="filtreTab w-100 d-flex  ">
        <div class="dropdown">
          <button aria-expanded="false" type="button" id="dropdownMenuButton" data-toggle="dropdown" class="btn flex-fill dropdown-toggle "> PAYS</button>
          <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
          <li ><a class="dropdown-item btn" type="button" href="#">Action</a></li>
          
        </ul>
        </div>
          <a type="button" class="btn flex-fill"> TYPE MOBILITE</a>
          <a type="button" class="btn flex-fill "> STATUT CONTRAT</a>
      </div>
    </div>

    <h1 class="col-4 text-center">GESTION DES DESTINATIONS</h1>

    <div class="col-4 text-right">
        <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#exempleModalD">
            Ajouter une destination
        </button>
    </div>

  </div>
  

  <TableDestinations @set="setDestination" @delete="deleteDestination" v-bind:destinations="listeDestinationsTab"/>
  <FormAddDestination @ajouter="addDest" />
  </div>
  
</template>

<script setup>

    import {reactive, onMounted} from 'vue'
    import TableDestinations from './tableDestinations/TableDestinations.vue'
    import FormAddDestination from './formAddDestination/FormAddDestination.vue';
    const listeDestinations =  reactive([])
    const listeDestinationsTab = reactive([])
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
        listeDestinationsTab.splice(0,listeDestinationsTab.length) 

        let url = `http://localhost:8989/api/destinations`
        fetch(url)
        .then((res)=>{return res.json()})
        .then((json)=>{
            let isValide;
            let date;
            let actuelDate = new Date();
        
            console.log(actuelDate)
            for(let d of json._embedded.destinations){

                
                    date=new Date(d.dateFinDeContratIsis)
                    dateDiff(actuelDate,date).day
                    if(dateDiff(actuelDate,date).day<385){
                                isValide = "Bientôt Expiré"
                    }
                    else{

                        isValide = "Valide"
                    }
                    listeDestinations.push([d,isValide])
                    listeDestinationsTab.push([d,isValide])
                    

            }
            console.log(listeDestinations)
        })
    }

    function deleteDestination(id){
        console.log('delete',id)
        let myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        const fetchOptions = {
                method: "DELETE",
                headers: myHeaders
                };
        let url = `http://localhost:8989/api/destinations/${id}`
        fetch(url,fetchOptions)
        .then(()=>{getDestinations()})
        .catch((err)=>{
            window.alert("Vous ne pouvez pas supprimer une destination qui est liée à une ou plusieurs mobilités")
            console.log("message d'erreur : ",err)})
    }
    

    function setDestination(destination){
        document.getElementById("nomEtablissement").value=destination[0].nomEtablissementAccueil
        document.getElementById("nomVille").value=destination[0].ville
        document.getElementById("nomPays").value=destination[0].pays
        document.getElementById(`${destination[0].typeMobilite}`).selected = true
        document.getElementById("semestres").value= parseInt(destination[0].nbPlaceSemestre)
        document.getElementById("nbPlaceAnnee").value=parseInt(destination[0].nbPlaceAnnee)
        document.getElementById("dateFinContrat").value=destination[0].dateFinDeContratIsis

        document.getElementById("btnSub").addEventListener('click',()=>{
            console.log(destination)
            updateDestination(destination)
        })
    }

    function updateDestination(destination){

           let nomEtablissement = document.getElementById("nomEtablissement").value
           let nomVille = document.getElementById("nomVille").value
           let nomPays = document.getElementById("nomPays").value
           let typeMobilite = document.getElementById("typeMobilite").value
           let nbPlaceSemestre = document.getElementById("semestres").value
           let nbPlaceAnnee= document.getElementById("nbPlaceAnnee").value
           let date = document.getElementById("dateFinContrat").value

            const url = `http://localhost:8989/api/destinations/${destination[0].id}` // l’url de l'API

            let myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");
            const fetchOptions = {  method:"PUT", 
                                    headers: myHeaders, 
                                    body: JSON.stringify({
                                        nomEtablissementAccueil:nomEtablissement,
                                        dateFinDeContratIsis:date,
                                        nbPlaceAnnee:nbPlaceAnnee,
                                        nbPlaceSemestre:nbPlaceSemestre,
                                        typeMobilite:typeMobilite,
                                        ville:nomVille,
                                        pays:nomPays
                                        })};
            fetch(url,fetchOptions)
            .catch((error) => console.log(error));

    }

    function addDest() {
    let nomEtablissement = document.getElementById("addNomEtablissement").value
    let nomVille = document.getElementById("addNomVille").value
    let nomPays = document.getElementById("addNomPays").value
    let typeMobilite = document.getElementById("addTypeMobilite").value
    let nbPlaceSemestre = document.getElementById("addSemestres").value
    let nbPlaceAnnee= document.getElementById("addNbPlaceAnnee").value
    let date = document.getElementById("addDateFinContrat").value
    if(nomEtablissement==""|| nomVille==""||nomPays==""){
      window.alert("Veuillez remplir les 4 premiers champs!")
    }
    else{
    const url = `http://localhost:8989/api/destinations` // l’url de l'API

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const fetchOptions = {  method:"POST", 
                            headers: myHeaders, 
                            body: JSON.stringify({
                                nomEtablissementAccueil:nomEtablissement,
                                dateFinDeContratIsis:date,
                                nbPlaceAnnee:nbPlaceAnnee,
                                nbPlaceSemestre:nbPlaceSemestre,
                                typeMobilite:typeMobilite,
                                ville:nomVille,
                                pays:nomPays
                                })};
    fetch(url,fetchOptions)
    .then(()=>{getDestinations()})
    .catch((error) => console.log(error));}
}

    onMounted(()=>{
        getDestinations()
    })
</script>

<style>
</style>