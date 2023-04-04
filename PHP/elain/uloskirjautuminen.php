<?php
session_start();
include("kantayhteys.php");
?>
<?php

/*ini_set('display_errors', 1);	   //poista tai kommentoi nämä rivit, kun sivusto toimii
ini_set('display_startup_errors', 1);   //poista tai kommentoi nämä rivit, kun sivusto toimii
error_reporting(E_ALL);	 //poista tai kommentoi nämä rivit, kun sivusto toimii*/

header("Content-Type: text/html; charset=utf-8");
if($_SESSION['LOGGEDIN'] == 1) {

// Tyhjentää session muuttujat
$_SESSION = array();

// Poistaa session keksit
if (ini_get("session.use_cookies")) {
    $params = session_get_cookie_params();
    setcookie(session_name(), '', time() - 42000,
        $params["path"], $params["domain"],
        $params["secure"], $params["httponly"]
    );
}

// Tuhoaa session
session_destroy();

echo "Uloskirjautuminen onnistui! <a href='kirjautuminen.html'>Kirjaudu</a>
uudelleen sisään tai <a href='index.php'>palaa etusivulle</a>.";
}
?>
