<?php
/*
  ini_set('display_errors', 1);
  ini_set('display_startup_errors', 1);
  error_reporting(E_ALL);
*/
  session_start();

  //MySQL Tietokanta yhteyden muodostus
  include 'kantayhteys.php';
  $conn = haeTietokantaYhteys();
  // asetetaan tyyli
  echo '<link href="css/phpstyle.css" rel="stylesheet">';
  $sivu = $_POST['lomaketunnistin'];

  $username = mysqli_real_escape_string($conn, $_POST['username']);
  $password = mysqli_real_escape_string($conn, $_POST['password']);
  
  // Rekisteröityiminen
  if($sivu == 'kuusi') {
    $email = mysqli_real_escape_string($conn, $_POST['kayttaja_sahkoposti']);

         // password_hash() functio salasanan suolaamiseen bcryptillä
  $password_hash = password_hash($password, PASSWORD_DEFAULT);
   
    $sql = "INSERT INTO kayttajat (kayttaja_tunnus, kayttaja_salasana, kayttaja_sahkoposti)
    VALUES ('$username', '$password_hash', '$email')";

    $query = mysqli_query($conn, "SELECT * FROM kayttajat WHERE kayttaja_tunnus = '$username'");

    if(mysqli_num_rows($query) !== 0) {
        echo "<p>Tunnus on jo käytössä! <a href='rekisterointi.html'>Kokeile uudestaan</a>.</p>";
    } else {
        if (mysqli_query($conn, $sql)) {
            echo "<p>Rekisteröinti onnistui! <a href='kirjautuminen.html'>Kirjaudu sisälle</a> palveluun.</p>";
        } else {
            echo "<p>Virhe tietokantayhteydessä</p>: " . $sql . "<br>" . $conn->error;
        }
    }
  }

  // kirjautuminen

  if($sivu == 1) {

    
    $query = mysqli_query($conn, "SELECT * FROM kayttajat WHERE kayttaja_tunnus ='$username'");
    $tiedot = mysqli_fetch_array($query) or die(mysqli_error());
    
    if (mysqli_num_rows($query) == 0) {
       
        echo "<p>Kirjautuminen ei onnistunut. Joko kirjoitit tiedot väärin tai et ole <a href='rekisterointi.html'>rekisteröitynyt</a> palvelun käyttäjäksi. Kokeile <a href='kirjautuminen.html'>uudestaan</a>.</p>";
    } else {
        // käyttäjä löytyi, tarkista salasana
        if (!password_verify($password, $tiedot['kayttaja_salasana'])) {
            
            echo "<p>Kirjautuminen ei onnistunut. Joko kirjoitit tiedot väärin tai et ole <a href='rekisterointi.html'>rekisteröitynyt</a> palvelun käyttäjäksi. Kokeile <a href='kirjautuminen.html'>uudestaan</a>.</p>";
        } else {
            
            echo "<p>Kirjautuminen onnistui! <br> <a href='index.php'>Siirry palveluun</a>.</p>";
            $_SESSION["kayttaja_id"] = $tiedot['kayttaja_id'];
            $_SESSION["kayttaja_taso"] = $tiedot['kayttaja_taso'];
            $_SESSION["kayttaja_tunnus"] = $tiedot['kayttaja_tunnus'];
            $_SESSION["kayttaja_salasana"] = $tiedot['kayttaja_salasana'];
            $_SESSION["kayttaja_sahkoposti"] = $tiedot['kayttaja_sahkoposti'];
            $_SESSION['LOGGEDIN'] = 1;
        }
    }
   }

  // käyttäjätietojen muuttaminen

  if($sivu == 2) {
    $kayttaja_uusisalasana = $_POST['kayttaja_uusisalasana'];
    $kayttaja_uusisalasana = password_hash($kayttaja_uusisalasana, PASSWORD_DEFAULT);  // Lisätty Bcrypt
   // $kayttaja_uusisalasana = hash('sha256', $salt.$kayttaja_uusisalasana);
    $kayttaja_uusisahkoposti = $_POST['kayttaja_uusisahkoposti'];
    
    function vaihdaSahkoposti($kayttaja_uusisahkoposti) {
      $_SESSION["kayttaja_sahkoposti"] = $kayttaja_uusisahkoposti;
      global $username;
      global $conn;
      
      if (!empty($kayttaja_uusisahkoposti)) {
          $query = mysqli_query($conn, "UPDATE kayttajat SET kayttaja_sahkoposti='$kayttaja_uusisahkoposti' WHERE kayttaja_tunnus = '$username'");
      }
      else {
          echo "<p>Jätit kentän tyhjäksi. Kokeile <a href='tiedot.php'>uudestaan</a>.</p>";
      }
  }

  $query = mysqli_query($conn, "SELECT * FROM kayttajat WHERE kayttaja_tunnus = '$username'");
  $tiedot = mysqli_fetch_array($query) or die(mysqli_error($conn));
  
  if (empty($password)) {
      vaihdaSahkoposti($kayttaja_uusisahkoposti);

      echo "<p>Tietojen muutos onnistui. <br> <a href='index.php'>Palaa etusivulle</a>.</p>";
  }
  else {
    if (!password_verify($password, $tiedot['kayttaja_salasana']) || empty($kayttaja_uusisalasana)) {  // Lisätty Bcrypt
     //if ($tiedot['kayttaja_salasana'] !== $password || empty($kayttaja_uusisalasana)) {
          echo "<p>Syötit väärän salasanan tai jätit tietoja täyttämättä. Kokeile <a href='tiedot.php'>uudestaan</a>.</p>";
      } 
      else {
          $query= mysqli_query($conn, "UPDATE kayttajat SET kayttaja_salasana='$kayttaja_uusisalasana' WHERE kayttaja_tunnus = '$username'");
          vaihdaSahkoposti($kayttaja_uusisahkoposti);
          
          echo "<p>Tietojen muutos onnistui. <br> <a href='index.php'>Palaa etusivulle</a>.</p>";
      }
  }
}
  mysqli_close($conn);

?>