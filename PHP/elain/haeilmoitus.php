<?php
  session_start();
  include("kantayhteys.php");
  $conn = haeTietokantaYhteys();
  
  /*ini_set('display_errors', 1);
  ini_set('display_startup_errors', 1);
  error_reporting(E_ALL);*/

  header("Content-Type: text/html; charset=utf-8");
  // asetetaan tyyli
  echo '<link href="css/phpstyle.css" rel="stylesheet">';

  date_default_timezone_set('Europe/Helsinki'); // Kellonaika
  if (isset($_POST['haku'])) {
    $haku=$_POST['haku'];
  }
    echo "<h3>Haun tulokset:</h3><br> <form action='haeilmoitus.php' method='post'><input
    name='haku' type='text'><input type='submit' name='submit' value='Hae'></form></p>";

    if((isset($_POST['submit'])) AND (!empty($haku))) {
    $query=("SELECT * FROM ilmoitukset INNER JOIN kayttajat ON ilmoitukset.ilmoitus_id=kayttajat.kayttaja_id
     WHERE elain_laji LIKE '%" . $haku . "%'  
     OR elain_nimi LIKE '%" . $haku . "%'
     OR elain_syntymaaika LIKE '%" . $haku . "%'
     OR elain_sukupuoli LIKE '%" . $haku . "%'
     OR elain_rekisteroity LIKE '%" . $haku . "%'
     OR elain_sijoitus LIKE '%" . $haku . "%'
     OR elaimen_isa LIKE '%" . $haku . "%'
     OR elaimen_aiti LIKE '%" . $haku . "%'
     OR astutus_pvm LIKE '%" . $haku . "%'
     OR arvioitu_synnytys LIKE '%" . $haku . "%'
     OR elain_poikueet LIKE '%" . $haku . "%'
     OR lisa_tietoja LIKE '%" . $haku . "%' ");

    $result=mysqli_query($conn, $query);
    $num=mysqli_num_rows($result);

    if ($num == 0) {
      echo "<p>Hakusanallesi '<b>" . $haku . "</b>' ei löytynyt eläimiä.</p>";
    } 
    else {
      echo "<p>Hakusanallesi '<b>" . $haku . "</b>' löytyi eläimiä seuraavasti:</p> <br>";

      $i=0;
      while ($i < $num) {
      
      $row = mysqli_fetch_assoc($result);
      $elain_id = $row["elain_id"];
      $elain_laji = $row["elain_laji"];
      $elain_sukupuoli = $row['elain_sukupuoli'];
      $elain_rekisteroity = $row['elain_rekisteroity'];
      $elain_sijoitus = $row['elain_sijoitus'];

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
      if ($elain_sukupuoli == 1) {
        $elain_sukupuoli = "Naaras";
      }
      if ($elain_sukupuoli == 2) {
        $elain_sukupuoli = "Uros";
      }
      if ($elain_rekisteroity == 1) {
        $elain_rekisteroity = "Kyllä";
      }
      if ($elain_rekisteroity == 2) {
        $elain_rekisteroity = "Ei";
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

      $elain_nimi = $row["elain_nimi"];
      $lisa_tietoja = $row["lisa_tietoja"];
      $elain_syntymaaika = $row['elain_syntymaaika'];
      $elain_rotu = $row['elain_rotu'];
      $elain_vari = $row['elain_vari'];
      $elaimen_isa = $row['elaimen_isa'];
      $elaimen_aiti = $row['elaimen_aiti'];
      $astutus_pvm = $row['astutus_pvm'];
      $arvioitu_synnytys = $row['arvioitu_synnytys'];
      $elain_poikueet = $row['elain_poikueet'];
      $elain_nimi = $row["elain_nimi"];
      $lisa_tietoja=$row["lisa_tietoja"];
      $ilmoitus_paivays=$row["ilmoitus_paivays"];
      $ilmoitus_oikeapaivays = date("d-m-Y", strtotime($ilmoitus_paivays));
      $ilmoitus_id=$row["ilmoitus_id"];
      $kayttaja_tunnus= $row["kayttaja_tunnus"];
      $kayttaja_sahkoposti=$row["kayttaja_sahkoposti"];


      echo "<p><table width='500'><tr><td bgcolor='#AABBCC'>
    <b>$elain_laji:$elain_nimi</b></td></tr>
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
     
      if ((isset($_SESSION['kayttaja_id']) && $_SESSION['kayttaja_id'] ==
          $ilmoitus_id) or (isset($_SESSION['kayttaja_taso']) &&
          $_SESSION['kayttaja_taso'] == "admin")) {
        echo "<tr><td><form action='poistailmoitus.php'
        method='post'/><input type='hidden' name='poista'
        value='1'><input type='hidden' name='poista_id'
        value='$elain_id'><input type='submit'
        value='Poista'></form><form action='muokkaailmoitus.php'
        method='post'/><input type='hidden' name='muokkaa_id'
        value='$elain_id'/><input type='submit'
        value='Muokkaa'/></form></td></tr>";
      }
      echo "</table></p>";
        $i++;
      }
    }
  } else {
    echo "Syötä hakusana yllä olevaan kenttään";
  }
  echo "<br>(<a href='haeilmoitus.php'>Tyhjennä haku</a>) - (<a href='index.php'>Palaa etusivulle</a>)";
?>