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
            <form @submit.prevent="updateEtud">
                <div class="form-group">
                    <label for="nom" class="font-weight-bold">Nom :</label>
                    <input id="nomEtudToEdit" class="form-control" name="nom" type="text" v-model="nom" placeholder="Entrez le nom de l'étudiant ..." />
                </div>
                <div class="form-group">
                    <label for="prenom" class="font-weight-bold">Prénom :</label>
                    <input id="prenomEtudToEdit" class="form-control" name="prenom" type="text" v-model="prenom" placeholder="Entrez le prénom de l'étudiant ..." />
                </div>
                <div class="form-group">
                    <label for="ine" class="font-weight-bold">INE :</label>
                    <input id="ineEtudToEdit" class="form-control" name="numEtud" type="number" v-model="ine" placeholder="Entrez l'INE de l'étudiant ..." />
                </div>
                <div class="form-group">
                    <label for="promotion" class="font-weight-bold">Promotion :</label>
                    <input id="promotionEtudToEdit" class="form-control" name="promo" type="number" v-model="promotion" placeholder="Saisir une promotion ..." />
                </div>
                <input id="idEtudToEdit" style="display: none;"/>
                <div class="modal-footer">
                    <button type="button" class="btnBleu" data-bs-dismiss="modal">Annuler</button>
                    <input id="Bouton" type="submit" class="btnOrange" value="Modifier" data-bs-dismiss="modal"/>
                 </div>
            </form>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script setup>
    import {ref, defineEmits} from 'vue'
    import { createToast } from 'mosha-vue-toastify';
    import 'mosha-vue-toastify/dist/style.css'

    defineProps(["etudiant"]);
    const emit = defineEmits(['update_ok'])

    /**
     * 
     * @param etudiant
     * @return
     * Fonction qui modifie un étudiant par méthode PUT
     */
    function updateEtud() {
        let nom = document.getElementById("nomEtudToEdit").value
        let prenom = document.getElementById("prenomEtudToEdit").value
        let ine = document.getElementById("ineEtudToEdit").value
        let promotion = document.getElementById("promotionEtudToEdit").value
        let id = document.getElementById("idEtudToEdit").value
        const url = `/api/etudiants/${id}`
     
        let myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
        myHeaders.append("Authorization", localStorage.getItem('jwt'))
        const fetchOptions = {method:"PUT", headers: myHeaders, body: JSON.stringify({nom:nom, numEtud:ine, prenom:prenom, promo:promotion})};
        fetch(url,fetchOptions)
        .then((response)=>{
            if(response.status === 400){
                toastDanger('Modification refusée')
            }
            else{
                toastSuccess('Modification effectuée')
                emit('update_ok')
            }
        })
        .catch((error) => console.log(error));
    }

    function toastDanger (title, message) {
      createToast({ title: title, description: message}, {type: 'danger'})
    }

    function toastSuccess (message) {
      createToast(message, {type: 'success'})
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