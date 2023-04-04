<!DOCTYPE html>
<html lang="fi">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Document</title>
</head>
<body>
  

<?php
  session_start();
  include("kantayhteys.php");
  $conn = haeTietokantaYhteys();
  
  /*ini_set('display_errors', 1);	//poista tai kommentoi nämä rivit, kun sivusto toimii
  ini_set('display_startup_errors', 1);	//poista tai kommentoi nämä rivit
  error_reporting(E_ALL);	//poista tai kommentoi nämä rivit*/

  header("Content-Type: text/html; charset=utf-8");

  // asetetaan oletus aikavyöhyke
  date_default_timezone_set('Europe/Helsinki'); 
  // asetetaan tyyli
  echo '<link href="css/indexstyle.css" rel="stylesheet">'; 

  echo "<h2>Eläinrekisteri</h2>";

  if (isset($_SESSION['LOGGEDIN']) && $_SESSION['LOGGEDIN'] == 1) {
    echo "Tervetuloa käyttämään eläinrekisteriä, " . $_SESSION["kayttaja_tunnus"] ."!<br>";

    echo "(<a href='lisaailmoitus.php'>Lisää eläin</a>) - (<a href='tiedot.php'>Muuta
    tietojasi</a>) - (<a href='uloskirjautuminen.php'>Kirjaudu ulos</a>)<br>";
  }
  else {
    echo "<a href='kirjautuminen.html'>Kirjaudu sisään</a> tai <a
    
    href='rekisterointi.html'>rekisteröidy palveluun</a>.";
  }

  echo "<h3>Eläimet:</h3>";

  echo "Hae eläimiä:<br> <form action='haeilmoitus.php' method='post'><input
  name='haku' type='text'><input type='submit' name='submit' value='Hae'></form>";

    // ilmoitusten tuonti
// Vain käyttäjän lisäämät ilmoitukset
// Prepared statement versio

$stmt = $conn->prepare("SELECT * FROM ilmoitukset INNER JOIN kayttajat ON ilmoitukset.ilmoitus_id = kayttajat.kayttaja_id WHERE kayttajat.kayttaja_tunnus = ?");
$stmt->bind_param("s", $_SESSION["kayttaja_tunnus"]);
$stmt->execute();
$result = $stmt->get_result();

if (!$result) {
  printf("Error: %s\n", $conn->error);
  exit();
}

$num = mysqli_num_rows($result);

$i = 0;
while ($i < $num) {
  
    $row = mysqli_fetch_assoc($result);
    $elain_id = $row["elain_id"];
    $elain_laji = $row["elain_laji"];

    if (false === $elain_laji) {
      echo mysql_error();
    }

    if ($elain_laji == 1) {
      $elain_laji = "Kani";
    } 
    if ($elain_laji == 2) {
      $elain_laji = "Kissa";
    }
    if ($elain_laji == 3) {
      $elain_laji = "Koira";
    }
    

    $elain_nimi = $row["elain_nimi"];
    $lisa_tietoja = $row["lisa_tietoja"];
    $elain_syntymaaika = $row['elain_syntymaaika'];
    $elain_rotu = $row['elain_rotu'];
    $elain_sukupuoli = $row['elain_sukupuoli'];
    if (false === $elain_sukupuoli) {
      echo mysql_error();
    }

    if ($elain_sukupuoli == 1) {
      $elain_sukupuoli = "Naaras";
    } 
    if ($elain_sukupuoli == 2) {
      $elain_sukupuoli = "Uros";
    }

    $elain_vari = $row['elain_vari'];
    $elain_rekisteroity = $row['elain_rekisteroity'];
    if (false === $elain_rekisteroity) {
      echo mysql_error();
    }

    if ($elain_rekisteroity == 1) {
      $elain_rekisteroity = "Kyllä";
    } 
    if ($elain_rekisteroity == 2) {
      $elain_rekisteroity = "Ei";
    }

    $elain_sijoitus = $row['elain_sijoitus'];
    if (false === $elain_sijoitus) {
      echo mysql_error();
    }

    if ($elain_sijoitus == 1) {
      $elain_sijoitus = "Tuotantoeläin";
    } 
    if ($elain_sijoitus == 2) {
      $elain_sijoitus = "Lihaksi";
    }
    if ($elain_sijoitus == 3) {
      $elain_sijoitus = "Myyntiin";
    }

    $elaimen_isa = $row['elaimen_isa'];
    $elaimen_aiti = $row['elaimen_aiti'];
    $astutus_pvm = $row['astutus_pvm'];
    $arvioitu_synnytys = $row['arvioitu_synnytys'];
    $elain_poikueet = $row['elain_poikueet'];
    $ilmoitus_paivays = $row["ilmoitus_paivays"];
    $ilmoitus_oikeapaivays = date("d-m-Y",strtotime($ilmoitus_paivays));
    $ilmoitus_id = $row["kayttaja_id"];
    $kayttaja_tunnus = $row["kayttaja_tunnus"];
    $kayttaja_sahkoposti = $row["kayttaja_sahkoposti"]; 

   
    echo "<table width='500'><tr><td bgcolor='#AABBCC'>
    <b>$elain_laji: $elain_nimi</b></td></tr>
    <tr><td>Syntynyt: $elain_syntymaaika</td></tr>
    <tr><td>Rotu: $elain_rotu</td></tr>
    <tr><td>Sukupuoli: $elain_sukupuoli</td></tr>
    <tr><td>Väri: $elain_vari</td></tr>
    <tr><td>Rekisteröinti: $elain_rekisteroity</td></tr>
    <tr><td>Käyttötarkoitus: $elain_sijoitus</td></tr>
    <tr><td>Isä: $elaimen_isa</td></tr> 
    <tr><td>Äiti: $elaimen_aiti</td></tr>
    <tr><td>Astutuspvm: $astutus_pvm</td></tr> 
    <tr><td>Arvio synnytyspvm: $arvioitu_synnytys</td></tr> 
    <tr><td>Syntyneita poikueita: $elain_poikueet</td></tr>
    <tr><td>Lisätietoja: $lisa_tietoja</td></tr>
    <tr><td>Eläin lisätty: $ilmoitus_oikeapaivays</td></tr>";
    

    if ((isset($_SESSION['kayttaja_id']) AND $_SESSION['kayttaja_id'] == $ilmoitus_id)
    OR (isset($_SESSION['kayttaja_taso']) AND $_SESSION['kayttaja_taso'] == "admin")) {

      echo "<tr><td><form action='poistailmoitus.php' method='post'/><input
      type='hidden' name='poista' value='1'><input type='hidden' name='poista_id'
      value='$elain_id'><input type='submit' value='Poista'></form>

      <form action='muokkaailmoitus.php' method='post'/><input type='hidden'
      name='muokkaa_id' value='$elain_id'><input type='submit'
      value='Muokkaa'></form></td></tr>";
    }

    echo "</table></p>";
    $i++;
  }
  if($i == 0) {
    echo "<br><p><b>Rekisteriin ei ole vielä lisätty eläimiä!</b></p>";
  }
?>
</body>
</html>
 
    
    
   