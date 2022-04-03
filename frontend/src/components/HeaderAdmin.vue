<template>

    <nav class="navbar navbar-expand-lg row">
        
        <a class="navbar-brand col ms-4" href="#">
            <img src="../views/admin/accueil/img/ISIS-logo.png" alt="logo ISIS" height="60em"/>
        </a>
        <div class="navbar-nav col-9 titres">
            <a class="nav-item active" href="#">Accueil</a>
            <a class="nav-item" href="#">Etudiants</a>
            <a class="nav-item" href="#">Destinations</a>
            <a class="nav-item" href="#">Mobilités</a>
            <a class="nav-item" href="#">Documents administratifs</a>
            <a class="nav-item" href="#">Site public</a>
        </div>

        <div class="col text-center dropdown btn-group">

            <button class="btn dropdown-toggle " type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <img src="../views/admin/accueil/img/utilisateur.png" alt="utilisateur" height="30em"/>
            </button>
            <div class="dropdown-menu dropdown-menu-right text-center" id="menuDeroulant" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="#">Nom Prénom</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item text-wrap" href="#"><img class="mr-2" src="../views/admin/accueil/img/ajoutAdmin.svg" alt="logo d'ajout d'un admin"/>Ajouter un administrateur</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#"><img class="mr-2" src="../views/admin/accueil/img/deconnexion.svg" alt="logo deconnexion"/>Déconnexion</a>
            </div>
        </div>                      

    </nav>
</template>

<script>

</script>

<style>

nav
{
    background-color: #022e51;
    font-family: 'Bebas Neue', sans-serif;
    
}

.nav-item:hover{
    text-decoration: underline #b74803 ;
    color: #f3f3f3 ;
}

.active{
    text-decoration: underline #b74803 ;
}

div a {
    color: white;
    text-decoration : none;
    
}

.titres{
    display: flex;
    flex-wrap: nowrap;
    justify-content: space-around;
    font-size: 25px;
}

.bordure{
    border: solid 1px red
}

#separateur{
    border-top: #f3f3f3;
}

.dropdown-menu {
  background:#022e51 !important;
  color:#f3f3f3 !important;
  width: 2%;
}

.dropdown-menu a {
    color: #f3f3f3;
    font-size:1.1em;
}

.dropdown-menu a:hover {
    background-color: #b74803;
    color: #f3f3f3;

}


</style>