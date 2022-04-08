<template>
<div class="  container table-responsive  ">
  <table class="table table-striped mh-75  table-bordered overflow-scroll ">
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
    <td class="text-center ">{{destination[0].ville}}</td>
    <td class="text-center ">{{destination[0].pays}}</td>
    <td class="text-center ">{{destination[0].typeMobilite}}</td>
    <td style="d-flex ">
        <div class="text-center ">{{destination[0].nbPlaceAnnee}}/an </div>
        <div class="text-center ">{{destination[0].nbPlaceSemestre}}/semestre </div>
    </td>
    <td class="text-center ">{{destination[1]}}</td>
    <td class="text-center"> 
        <div class="text-center d-flex flex-row w-75 justify-content-around ">
           
        <a @click="setDestination(destination)" data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn" type="button" > <img v-bind:src="edit" alt="edit"></a>
        
        <a @click="deleteDestination(destination[0].id)" class="btn" type="button"> <img v-bind:src="poubelle" alt="trash"></a>
        </div> 
    </td>
    </tr>
    
    
  </tbody>
</table>
<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade " id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">MODIFIER UNE DESTINATION</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form class="container-fluid" >
          <div class="w-75 row m-auto " >
                <div class="mb-3 row">
                    <div class="col">
                    <label for="nomEtablissement" class="form-label">NOM DE L'ETABLISSEMENT:</label>
                    <input type="text" class="form-control " id="nomEtablissement">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-6 ">
                        <label for="nomVille" class="form-label">VILLE:</label>
                        <input type="text" class="form-control w-75 " name="nomVille" id="nomVille">
                    </div>

                    <div class="col-6 d-flex flex-row justify-content-end ">
                      
                        <div class=" w-75">
                        <label for="nomPays" class="form-label">PAYS:</label>
                        <input type="text" class="form-control " name="nomPays" id="nomPays">
                        </div>
                    </div>
                </div>

                <div class="mb-3 row">
                    <div class="col">
                    <label for="typeMobilite">TYPE DE MOBILITE:</label>
                    <select id="typeMobilite" name="typeMobilite" class="form-select">
                            <option id="ETUDE" value="ETUDE">Etude</option>
                            <option id="STAGE" value="STAGE">Stage</option>
                            <option id="HUMANITAIRE" value="HUMANITAIRE">Humanitaire</option>
                    </select>
                    </div>
                </div>

                <div class="mb-3 row">
                    <div class="col">
                    <label for="semestres" class="form-label">NOMBRE DE PLACE PAR SEMESTRE:</label>
                    <input type="number" min="0" class="form-control" name="semestres" id="semestres">
                    </div>
                </div>

                <div class="mb-3 row">
                    <div class="col">
                    <label for="nbPlaceAnnee" class="form-label">NOMBRE DE PLACE PAR ANNEE:</label>
                    <input type="number" min="0" class="form-control " name="nbPlaceAnnee" id="nbPlaceAnnee">
                    </div>
                </div>

                <div class="mb-3 row">
                    <div class="col">
                    <label for="dateFinContrat" class="form-label">DATE DE FIN DE CONTRAT:</label>
                    <input type="date"  class="form-control " name="dateFinContrat" id="dateFinContrat">
                    </div>
                </div>

                <div class="mb-3 row">
                    <div class="col">
                    <label for="image" class="form-label">CHOISIR/MODIFIER L'IMAGE:</label>
                    <input type="file"  class="form-control " name="image" id="image">
                    </div>
                </div>
                
        
        </div>
                  
        <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="submit" id="btnSub" class="btn btn-primary">Save changes</button>
      </div>
    </form>
      </div>
      
    </div>
  </div>
</div>
</div>
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

                
                    date=new Date(d.dateFinDeContratIsis)
                    dateDiff(actuelDate,date).day
                    if(dateDiff(actuelDate,date).day<385){
                                isValide = "Bientôt Expiré"
                    }
                    else{

                        isValide = "Valide"
                    }
                    listeDestinations.push([d,isValide])
                    
                    

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

    onMounted(()=>{
        getDestinations()
    })
</script>

<style>

    .styleTab{
        border-radius: 1000px;
        border: 5px ridge #022e51;
    }
    thead{
        font-family: 'Bebas Neue', sans-serif;
        color: white;
        background-color: #022e51;
    }
</style>