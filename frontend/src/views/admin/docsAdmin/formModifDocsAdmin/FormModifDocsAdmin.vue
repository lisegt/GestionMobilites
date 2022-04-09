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
            <form>
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