<?php
  session_start();
  include("kantayhteys.php");
  $conn = haeTietokantaYhteys();

   /* ini_set('display_errors', 1);               
    ini_set('display_strartup_errors', 1);      
    error_reporting(E_ALL);*/

  header("Content-Type: text/html; charset=utf-8");
  // asetetaan tyyli
  echo '<link href="css/phpstyle.css" rel="stylesheet">';

  $sivu = $_POST['lomaketunnistin'];

    if($sivu == 1) {
    $elain_laji = $_POST['elain_laji'];
    $elain_nimi = $_POST['elain_nimi'];
    $elain_syntymaaika = $_POST['elain_syntymaaika'];
    $elain_rotu = $_POST['elain_rotu'];
    $elain_sukupuoli = $_POST['elain_sukupuoli'];
    $elain_vari = $_POST['elain_vari'];
    $elain_rekisteroity = $_POST['elain_rekisteroity'];
    $elain_sijoitus = $_POST['elain_sijoitus'];
    $elaimen_isa = $_POST['elaimen_isa'];
    $elaimen_aiti = $_POST['elaimen_aiti'];
    $astutus_pvm = $_POST['astutus_pvm'];
    $arvioitu_synnytys = $_POST['arvioitu_synnytys'];
    $elain_poikueet = $_POST['elain_poikueet'];
    $lisa_tietoja = $_POST['lisa_tietoja'];
    $ilmoitus_paivays = $_POST['ilmoitus_paivays'];
    $ilmoitus_id = $_POST['ilmoitus_id'];

    
  
    if(!empty($elain_laji) && !empty($elain_nimi)) {
      mysqli_query($conn, "INSERT INTO ilmoitukset (elain_laji, elain_nimi, elain_syntymaaika, elain_rotu, elain_sukupuoli, elain_vari, elain_rekisteroity, elain_sijoitus, elaimen_isa, elaimen_aiti, astutus_pvm, arvioitu_synnytys, elain_poikueet, lisa_tietoja, ilmoitus_paivays, ilmoitus_id)
      VALUES ('$elain_laji', '$elain_nimi', '$elain_syntymaaika', '$elain_rotu', '$elain_sukupuoli', '$elain_vari', '$elain_rekisteroity', '$elain_sijoitus', '$elaimen_isa', '$elaimen_aiti', '$astutus_pvm', '$arvioitu_synnytys', '$elain_poikueet', '$lisa_tietoja', '$ilmoitus_paivays', '$ilmoitus_id')");
      echo "<p>Eläimen lisääminen onnistui! Palaa <a href='index.php'>etusivulle</a>.</p>";
    } 
    else {
      echo "<p>Jätit tietoja täyttämättä. Ole hyvä ja <a href='lisaailmoitus.php'>täytä lomake uudestaan</a>.</p>";
    }
  }
  if($sivu == 2) {
    $elain_id=$_POST['elain_id'];
    $ilmoitus_uusilaji=$_POST['ilmoitus_uusilaji'];
    $ilmoitus_uusinimi=$_POST['ilmoitus_uusinimi'];
    $ilmoitus_uusisyntyma=$_POST['ilmoitus_uusisyntyma'];
    $ilmoitus_uusirotu=$_POST['ilmoitus_uusirotu'];
    $ilmoitus_uusisukupuoli=$_POST['ilmoitus_uusisukupuoli'];
    $ilmoitus_uusiväri=$_POST['ilmoitus_uusiväri'];
    $ilmoitus_uusirekisteroity=$_POST['ilmoitus_uusirekisteroity'];
    $ilmoitus_uusisijoitus=$_POST['ilmoitus_uusisijoitus'];
    $ilmoitus_uusiisä=$_POST['ilmoitus_uusiisä'];
    $ilmoitus_uusiäiti=$_POST['ilmoitus_uusiäiti'];
    $ilmoitus_uusiastutus=$_POST['ilmoitus_uusiastutus'];
    $ilmoitus_uusisynnytys=$_POST['ilmoitus_uusisynnytys'];
    $ilmoitus_uusipoikueet=$_POST['ilmoitus_uusipoikueet'];
    $ilmoitus_uusikuvaus=$_POST['ilmoitus_uusikuvaus'];

  if(!empty($ilmoitus_uusinimi) AND !empty($ilmoitus_uusilaji)) {
    mysqli_query($conn, "UPDATE ilmoitukset
     SET elain_laji = '$ilmoitus_uusilaji',
      elain_nimi = '$ilmoitus_uusinimi',
      elain_syntymaaika = '$ilmoitus_uusisyntyma',
      elain_rotu = '$ilmoitus_uusirotu',
      elain_sukupuoli = '$ilmoitus_uusisukupuoli',
      elain_vari = '$ilmoitus_uusiväri',
      elain_rekisteroity = '$ilmoitus_uusirekisteroity',
      elain_sijoitus = '$ilmoitus_uusisijoitus',
      elaimen_isa = '$ilmoitus_uusiisä',
      elaimen_aiti = '$ilmoitus_uusiäiti',
      astutus_pvm = '$ilmoitus_uusiastutus',
      arvioitu_synnytys = '$ilmoitus_uusisynnytys',
      elain_poikueet = '$ilmoitus_uusipoikueet',
      lisa_tietoja = '$ilmoitus_uusikuvaus'
      WHERE elain_id = '$elain_id'");
      echo "<p>Ilmoituksen muokkaaminen onnistui! Palaa <a href='index.php'>etusivulle</a>.</p>";
    }
    else {
      echo "<p>Jätit kenttiä tyhjksi. <a href='index.php'>Palaa etusivulle</a>
      ja muokkaa ilmoitusta uudelleen</a>.</p>";
    }
  }
  mysqli_close($conn);
?>