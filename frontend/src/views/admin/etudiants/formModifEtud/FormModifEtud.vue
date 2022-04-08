<template>
<div class="modal fade" id="modif" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title font-weight-bold" id="Label">Modifier un étudiant</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div id="form">
            <form @submit.prevent="modifEtud()">
                <div class="form-group">
                    <label for="nom" class="font-weight-bold">Nom :</label>
                    <input id="nom" class="form-control" name="nom" type="text" v-model="nom" placeholder="Entrez le nom de l'étudiant ..." required/>
                </div>
                <div class="form-group">
                    <label for="prenom" class="font-weight-bold">Prénom :</label>
                    <input id="prenom" class="form-control" name="prenom" type="text" v-model="prenom" placeholder="Entrez le prénom de l'étudiant ..." required/>
                </div>
                <div class="form-group">
                    <label for="ine" class="font-weight-bold">INE :</label>
                    <input id="ine" class="form-control" name="numEtud" type="number" v-model="ine" placeholder="Entrez l'INE de l'étudiant ..." required/>
                </div>
                <div class="form-group">
                    <label for="promotion" class="font-weight-bold">Promotion :</label>
                    <input id="promotion" class="form-control" name="promo" type="number" v-model="promotion" placeholder="Sélectionnez une promotion ..." required/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btnOrange" data-bs-dismiss="modal">Close</button>
                    <input id="Bouton" type="submit" class="btnOrange" value="Ajouter" />
                 </div>
            </form>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script setup>
defineProps(["etudiant"]);

/**
 * 
 * @param etudiant
 * @return
 * Fonction qui récupère les données pas encore modifiées
 */
function setEtud(etudiant){
    let nom = document.getElementById("nom").value = etudiant.nom
    let prenom = document.getElementById("prenom").value = etudiant.prenom
    let ine = document.getElementById("ine").value = etudiant.ine
    let promotion = document.getElementById("promotion").value = etudiant.promotion

    document.getElementById("Bouton").addEventListener('click',()=>{
        console.log(etudiant)
        updateEtud(etudiant)
    })
}

/**
 * 
 * @param etudiant
 * @return
 * Fonction qui modifie un étudiant par méthode PUT
 */
function updateEtud(etudiant) {
    let nom = document.getElementById("nom").value
    let prenom = document.getElementById("prenom").value
    let ine = document.getElementById("ine").value
    let promotion = document.getElementById("promotion").value

    const url = `http://localhost:8989/api/etudiants/` //l’url de l'API

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const fetchOptions = {method:"PUT", headers: myHeaders, body: JSON.stringify({nom:etudiant.nom, numEtud:etudiant.ine, prenom:etudiant.prenom, promo:etudiant.promotion})};
    fetch(url+`${etudiant.id}`,fetchOptions)
    .catch((error) => console.log(error));
}
</script>

<style scoped>
h5{
    font-size: 30px;
    line-height: 36px;
}
label{
    font-size: 24px;
    line-height: 29px;
}
h5,label{
    text-transform: uppercase;
    color: #022E51;
    font-family: 'Bebas Neue';
    font-weight: 400;
    font-style: normal;
    text-align: center;
}
.form-control{
    background: rgba(163, 180, 200, 0.35);
    border-radius: 10px;
    font-family: 'Montserrat';
    font-weight: 400;
    font-size: 20px;
    color: #022E51;
}
#modif{
    border-radius: 10px;
}
</style>