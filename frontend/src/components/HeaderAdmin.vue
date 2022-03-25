<template>

    <nav class="navbar navbar-expand row">
        
        <a class="navbar-brand col-1 ms-4" href="#">
            <img src="../views/admin/Accueil/img/ISIS-logo.png" alt="logo ISIS" height="60em"/>
        </a>
        <div class="navbar-nav col-10 titres">
            <a class="nav-item active" href="#">Accueil</a>
            <a class="nav-item" href="#">Etudiants</a>
            <a class="nav-item" href="#">Destinations</a>
            <a class="nav-item" href="#">Mobilités</a>
            <a class="nav-item" href="#">Documents administratifs</a>
            <a class="nav-item" href="#">Site public</a>
        </div>

        <div class="col-1">
            <img src="../views/admin/Accueil/img/utilisateur.png" alt="logo ISIS" height="30em"/>
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


</style>