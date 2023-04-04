<?php
  session_start();
  include("kantayhteys.php");
  $conn = haeTietokantaYhteys();

  /*
  ini_set('display_errors', 1);
  ini_set('display_startup_errors', 1);
  error_reporting(E_ALL);*/
  header("Content-Type: text/html; charset=utf-8");
  // asetetaan tyyli
  echo '<link href="css/phpstyle.css" rel="stylesheet">';

  
  $elain_id = $_POST['muokkaa_id'];
  if(isset($elain_id)) {

  }
  $query = mysqli_query($conn, "SELECT * FROM ilmoitukset WHERE elain_id = '$elain_id'");


  $row = mysqli_fetch_assoc($query);

  $elain_nimi = $row["elain_nimi"];
  $elain_laji = $row["elain_laji"]; 
  if ($elain_laji == 1) {
    $elain_laji = "Kani";
  }
  if ($elain_laji == 2) {
    $elain_laji = "Kissa";
  }
  if ($elain_laji == 3) {
    $elain_laji = "Koira";
  }
  
  $elain_syntymaaika = $row['elain_syntymaaika'];
  $elain_rotu = $row['elain_rotu'];

  $elain_sukupuoli = $row['elain_sukupuoli'];
  if ($elain_sukupuoli == 1) {
    $elain_sukupuoli = "Naaras";
  }
  if ($elain_sukupuoli == 2) {
    $elain_sukupuoli = "Uros";
  }

  $elain_vari = $row['elain_vari'];

  $elain_rekisteroity = $row['elain_rekisteroity'];
  if ($elain_rekisteroity == 1) {
    $elain_rekisteroity = "Kyllä";
  }
  if ($elain_rekisteroity == 2) {
    $elain_rekisteroity = "Ei";
  }

  $elain_sijoitus = $row['elain_sijoitus'];
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
  $lisa_tietoja = $row["lisa_tietoja"];


  if (isset($_SESSION['LOGGEDIN']) && $_SESSION['LOGGEDIN'] == 1) {
    echo "<form action='ilmoitushallinta.php' method='post'>";
    echo "<h3>Muokkaa ilmoitusta</h3>";
    echo "<p>Eläinlaji: <b>".$elain_laji."</b><br>Muuta:
    <select name='ilmoitus_uusilaji'><option value='1'>Kani</option>
    <option value='2'>Kissa</option><option value='3'>Koira</option></select></p>";
    echo "<p>Eläimen nimi: <input name='ilmoitus_uusinimi' type='text' size='50'
    value='$elain_nimi'</p>";
    echo "<p>Syntynyt: <input name='ilmoitus_uusisyntyma' type='text' size='50'
    value='$elain_syntymaaika'</p>";
    echo "<p>Rotu:<input name='ilmoitus_uusirotu' type='text' size='50'
    value='$elain_rotu'</p>";
    echo "<p>Sukupuoli: <b>".$elain_sukupuoli."</b><br>Muuta:
    <select name='ilmoitus_uusisukupuoli'><option value='1'>Naaras</option>
    <option value='2'>Uros</option></select></p>";
    echo "<p>Väri:<input name='ilmoitus_uusiväri' type='text' size='50'
    value='$elain_vari'</p>";
    echo "<p>Rekisteröity: <b>".$elain_rekisteroity."</b><br>Muuta:
    <select name='ilmoitus_uusirekisteroity'><option value='1'>Kyllä</option>
    <option value='2'>Ei</option></select></p>";
    echo "<p>Käyttötarkoitus: <b>".$elain_sijoitus."</b><br>Muuta:
    <select name='ilmoitus_uusisijoitus'><option value='1'>Tuotantoeläin</option>
    <option value='2'>Lihaksi</option><option value='3'>Myyntiin</option></select></p>";
    echo "<p>Isä:<input name='ilmoitus_uusiisä' type='text' size='50'
    value='$elaimen_isa'</p>";
    echo "<p>Äiti:<input name='ilmoitus_uusiäiti' type='text' size='50'
    value='$elaimen_aiti'</p>";
    echo "<p>Astutuspvm:<input name='ilmoitus_uusiastutus' type='text' size='50'
    value='$astutus_pvm'</p>";
    echo "<p>Arvio synnytyspvm: <input name='ilmoitus_uusisynnytys' type='text' size='50'
    value='$arvioitu_synnytys'</p>";
    echo "<p>Syntyneitä poikueita:<input name='ilmoitus_uusipoikueet' type='text' size='50'
    value='$elain_poikueet'</p>";
    echo "<p><label>Lisätietoja:</label><br> <textarea name='ilmoitus_uusikuvaus' rows='5' cols='80'>$lisa_tietoja</textarea></p>";
    echo "<input type='hidden' name='lomaketunnistin' value='2'>";
    echo "<input type='hidden' name='elain_id' value='$elain_id'>";
    echo "<p><input type='Submit' value='Lähetä'></p>";
    echo "</form>";
    echo "<p><a href='index.php'>Palaa edelliselle sivulle</a></p>";
} else {
    echo "Et voi muokata ilmoituksia, koska et ole kirjautunut sisään!";
  }

?>
