<template>
<div class="modal fade" id="modif" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title font-weight-bold" id="Label">Modifier le document</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div id="form">
            <form @submit.prevent="setDoc()">
                <div class="form-group">
                    <label for="intitule" class="font-weight-bold">Nom du document :</label>
                    <input id="intitule" class="form-control" name="intitule" type="text" v-model="intitule" placeholder="Entrez le nom du document ..." required/>
                </div>
                <div class="form-group">
                    <label for="description" class="font-weight-bold">Desciption :</label>
                    <input id="description" class="form-control" name="description" type="text" v-model="description" placeholder="Entrez une description du document ..." required/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btnOrange" data-bs-dismiss="modal">Close</button>
                    <input id="Bouton" type="submit" class="btnOrange" value="Modifier" />
                 </div>
            </form>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script setup>

defineProps(["doc"]);

/**
 * 
 * @param doc
 * @return
 * Fonction qui récupère les données pas encore modifiées
 */
function setDoc(doc){
    let intitule = document.getElementById("intitule").value = doc.nom
    let description = document.getElementById("description").value = doc.prenom

    document.getElementById("Bouton").addEventListener('click',()=>{updateEtud(doc)})
}

/**
 * 
 * @param doc
 * @return
 * Fonction qui modifie un étudiant par méthode PUT
 */
function updateEtud(doc) {
    let intitule = document.getElementById("intitule").value
    let description = document.getElementById("description").value

    const url = `http://localhost:8989/api/documents/` //l’url de l'API

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const fetchOptions = {method:"PUT", headers: myHeaders, body: JSON.stringify({intitule:doc.nom, description:doc.ine})};
    fetch(url+`${doc.id}`,fetchOptions)
    .catch((error) => console.log(error));
}
</script>

<style>
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