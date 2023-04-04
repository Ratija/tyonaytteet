<style><?php include 'C:/xampp/htdocs/osto/css/phpstyle.css'; ?></style>
<?php
  session_start();
  include("kantayhteys.php");
  $conn = haeTietokantaYhteys();

  /*ini_set('display_errors', 1);
  ini_set('display_startup_errors', 1);
  error_reporting(E_ALL);*/
  header("Content-Type: text/html; charset=utf-8");
  // asetetaan tyyli
  //echo '<link href="css/phpstyle.css" rel="stylesheet">';

  
  $poista = $_POST['poista'];
  $elain_id = $_POST['poista_id'];

  if (isset($poista)){
    $query= mysqli_query($conn, "DELETE FROM ilmoitukset WHERE elain_id = '$elain_id'");

    echo "Ilmoitus poistettu! <a href='index.php'>Palaa edelliselle sivulle</a>.";
  }
?>