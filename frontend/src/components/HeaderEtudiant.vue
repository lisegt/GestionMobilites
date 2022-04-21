<template>
<div class="row">
    <nav class="navbar navbar-dark navbar-expand-lg col">
        <a tag="img" class="navbar-brand col ms-4" href="/">
            <img src="../views/admin/accueil/img/ISIS-logo.png" alt=" w-50 h-50">
        </a>
        
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <div id="navbarContent" class="collapse navbar-collapse w-100 titres">
           
            <ul class="navbar-nav  align-items-center titres w-100 ">
                <li class="flex-fill text-center"> <a class="nav-item active" id="accueilNav" href="/">Accueil</a> </li>
                <li class="flex-fill text-center"> <a class="nav-item" id="destinationsNav" href="/etudiant/destinations">Destinations</a> </li>
                <li class="flex-fill text-center"> <a class="nav-item" id="docNav" href="/etudiant/documents">Documents administratifs</a> </li>
                <li class=" flex-fill text-center">
                    <div class="col text-center text-center dropdown btn-group">
                        <button class="btn dropdown-toggle text-center" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <img src="../views/admin/accueil/img/utilisateur.png" alt="utilisateur" />
                        </button>
                        <div class="dropdown-menu dropdown-menu-right text-center" id="menuDeroulant" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="/login"> <img src="../img/sign-in.svg" alt="logo connexion" height="20" class="mr-1"/> Se connecter</a>
                        </div>
                    </div>
                </li>
            </ul>
        </div>  
    </nav>
</div>
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