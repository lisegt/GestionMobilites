<template>
    <div class="modal fade" id="exempleModalAddDoc" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title font-weight-bold" id="Label">Ajouter un retour d'expérience</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      
        <div id="form">
            <form >
                <div class="modal-body">
                <div class="w-75 row m-auto " >
                <div class="mb-3 row">
                    <div class="col-6">
                    </div>
                        <div class="form-group">
                            <label for="file" class="font-weight-bold">Fichier à télécharger :</label>
                            <input class="form-control" @change="majFile" id="file" type="file" required name="file" />
                        </div>
                    </div>      
        
       
                </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btnOrange" data-bs-dismiss="modal">Close</button>
                    <input id="btnSubDoc" @click="$emit('ajouter')" type="submit" class="btnOrange" value="Ajouter" />
                 </div>
                 
            </form>
        
      </div>
    </div>
  </div>
</div>
</template>

<script setup>
    const emits = defineEmits(['updateFile','updateDoc'])
    function majFile(event){
        emits('updateFile',event)
    }

    function majDoc(event){
        emits('updateDoc',event)
    }

</script>