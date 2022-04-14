<template>
<div class="modal fade" id="ajout" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title font-weight-bold" id="Label">Ajouter un document</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div id="form">
            <form>
                <div class="form-group">
                    <label for="nom" class="font-weight-bold">Nom du document :</label>
                    <input v-model="intitule" id="intitule" class="form-control" name="intitule" type="text" placeholder="Entrez le nom du document ..." required/>
                </div>
                <div class="form-group">
                    <label for="prenom" class="font-weight-bold">Description :</label>
                    <input v-model="description" id="description" class="form-control" name="description" type="text" placeholder="Entrez une description du document ..." required/>
                </div>
                <!--Upload de fichier :-->
                <div>
                  <form method="POST" enctype="multipart/form-data" action="/upload">
                    <div class="form-group">
                      <label for="file" class="font-weight-bold">Fichier à télécharger :</label>
                      <input class="form-control" id="file" type="file" name="fichier" />
                    </div>
                    <div class="form-group">
                      <label class="font-weight-bold">Télécharger :</label>
                      <input class="form-control" type="submit" value="Upload" />
                    </div>
                  </form>
                </div>
                <div>
                  <div th:each="file : ${files}">
                    <a th:href="${file}" th:text="${file}" />
                  </div>
                </div>
                <!---->
                <div class="modal-footer">
                    <button type="button" class="btnOrange" data-bs-dismiss="modal">Close</button>
                    <input @click="$emit('post', intitule, description)" id="bouton" type="submit" class="btnOrange" value="Ajouter"  data-bs-dismiss="modal"/>
                 </div>
            </form>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script setup>
import {ref} from 'vue'

let intitule = ref('')
let description = ref('')
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
#ajout{
    border-radius: 10px;
}
</style>