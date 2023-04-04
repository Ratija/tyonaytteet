<!DOCTYPE html>
<html lang="fi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lisää eläin rekisteriin</title>
    <script type="text/javascript" src="js/kalenteri.js"></script>
</head>
<body style='background-image: url("kuvat/bg4.jpg"); background-size: cover;'> 

<?php

  session_start();
  include("kantayhteys.php");

  $uros_status = 'unchecked';
  $naaras_status = 'unchecked';
  $kylla_status = 'unchecked';
  $ei_status = 'unchecked';
  $liha_status = 'unchecked';
  $myynti_status = 'unchecked';
 

  ini_set('display_errors', 1);
  ini_set('display_startup_errors', 1);
  error_reporting(E_ALL);

  header("Content-Type: text/html; charset=utf-8");
  // Aikavyöhyke
  date_default_timezone_set('Europe/Helsinki');
  // asetetaan tyyli
  echo '<link href="css/phpstyle.css" rel="stylesheet">';

  $ilmoitus_aika = date("Y-m-d");
  $ilmoitus_id = $_SESSION["kayttaja_id"];
  
  if (isset($_POST['elain_sukupuoli'])) {
    $selected_radio = $_POST['elain_sukupuoli'];
      if ($selected_radio == 'uros') {
        $uros_status = 'checked';
      }
      else if ($selected_radio == 'naaras') {
        $naaras_status = 'checked';
      }
  }

  if (isset($_POST['elain_rekisteroity'])) {
    $selected_radio = $_POST['elain_rekisteroity'];
      if ($selected_radio == 'kylla') {
        $kylla_status = 'checked';
      }
      else if ($selected_radio == 'ei') {
        $ei_status = 'checked';
      }
  }

  if (isset($_POST['elain_sijoitus'])) {
    $selected_radio = $_POST['elain_sijoitus'];
    if ($selected_radio == 'tuotanto') {
      $tuotanto_status = 'checked';
    }
      if ($selected_radio == 'liha') {
        $liha_status = 'checked';
      }
      else if ($selected_radio == 'myynti') {
        $myynti_status = 'checked';
      }
  }  

  if (isset($_SESSION['LOGGEDIN']) && $_SESSION['LOGGEDIN'] == 1) {
    echo "<tr><td><form action='ilmoitushallinta.php' method='post'>";
    echo "<h3>Lisää eläin</h3>";
    echo "<p>Eläinlaji:<select name='elain_laji'><option value='1'>Kani</option><option value='2'>Kissa</option><option value='3'>Koira</option></select></p>";
    echo "<p>Eläimen nimi: <input name='elain_nimi' type='text' size='50'></p>";
    echo "<p>Syntymäaika: <input id=t1 name='elain_syntymaaika' type='text' size='50'></p>"; ?>
   
     <img src='kuvat/kalenteri2.png' alt='kalenterin kuva' title='Aseta syntymäaika' onClick="start_cal('t1','calendar1');" style="cursor: pointer;">  
    <div id="calendar1" style="position: absolute;top: 40%;left: 58%;z-index:1;"></div>
    
  <?php  
    echo "<p>Rotu: <input name='elain_rotu' type='text' size='50'></p>";
    echo "<p>Sukupuoli: <select name='elain_sukupuoli'><option value='1'>Naaras</option><option value='2'>Uros</option></select></p>";
    //echo "<p>Sukupuoli:</p>";
        ?>
 <!-- <INPUT TYPE = 'Radio' Name ='elain_sukupuoli'  value= 'naaras' <?PHP print $naaras_status; ?>>Naaras  -->
<!-- <INPUT TYPE = 'Radio' Name ='elain_sukupuoli'  value= 'uros' <?PHP print $uros_status; ?>>Uros-->
 <?php  
    echo "<p>Väri: <input name='elain_vari' type='text' size='50'></p>";
    echo "<p>Rekisteröity: <select name='elain_rekisteroity'><option value='1'>Kyllä</option><option value='2'>Ei</option></select></p>";
    //echo "<p>Rekisteröity:</p>"; 
    ?>
<!-- <INPUT TYPE = 'Radio' Name ='elain_rekisteroity'  value= 'kylla' <?PHP print $kylla_status; ?>>Kyllä  -->
<!-- <INPUT TYPE = 'Radio' Name ='elain_rekisteroity'  value= 'ei' <?PHP print $ei_status; ?>>Ei -->
<?php  
    echo "<p>Eläimen sijoitus: <select name='elain_sijoitus'><option value='1'>Tuotantoeläin</option><option value='2'>Lihaksi</option><option value='3'>Myyntiin</option></select></p>";
   // echo "<p>Eläimen sijoitus:</p>"; ?>
<!-- <INPUT TYPE = 'Radio' Name ='elain_sijoitus'  value= 'tuotanto' <?PHP print $myynti_status; ?>>Tuotantoeläin -->
<!-- <INPUT TYPE = 'Radio' Name ='elain_sijoitus'  value= 'liha' <?PHP print $liha_status; ?>>Lihaksi   -->
<!-- <INPUT TYPE = 'Radio' Name ='elain_sijoitus'  value= 'myynti' <?PHP print $myynti_status; ?>>Myyntiin -->
<?php 
    echo "<p>Isän nimi: <input name='elaimen_isa' type='text' size='50'></p>";
    echo "<p>Äidin nimi: <input name='elaimen_aiti' type='text' size='50'></p>";
    echo "<p>Astutuspäivämäärä: <input id=t2 name='astutus_pvm' type='text' size='50'></p>";?>
        
     <img src='kuvat/kalenteri2.png'  alt='kalenterin kuva' title='Aseta astutuspäivä' onClick="start_cal('t2','calendar2');" style="cursor: pointer;" >  
     <div id="calendar2" style="position: absolute;top: 130%;left: 60%;z-index:1;"></div>
     <br>
     <br>
    
     <?php
     
     echo "<b style='color:black; background-color:rgb(238, 238, 238);'>Arvioitu synnytys +30 päivää: <input id=t3 name='arvioitu_synnytys' type='text' size='50'></b>";?>
     <br>
     <img src='kuvat/kalenteri2.png'  alt='kalenterin kuva' title='Arvioi synnytyspäivä' onClick="arvioitu();" style="cursor: pointer;" >  
     <br>
     <br>
     <?php
    echo "<b style='color:black; background-color:rgb(238, 238, 238);'>Syntyneitä poikueita: <input name='elain_poikueet' type='text' size='50'></b>";
    echo "<p>Lisätietoja:</p><textarea name='lisa_tietoja' rows='4' cols='40'></textarea>";
    echo "<input type='hidden' name='ilmoitus_id' value='$ilmoitus_id'>";
    echo "<input type='hidden' name='ilmoitus_paivays' value='$ilmoitus_aika'>";
    echo "<input type='hidden' name='lomaketunnistin' value='1'>";
    echo "<p><input type='submit' value='Lisää rekisteriin'></p>";
    echo "</form></td></tr>";
    echo "<p><a href='index.php'>Palaa edelliselle sivulle.</a></p>";
    exit;
  } else {
    echo "Et voi lisätä ilmoituksia, koska et ole kirjautunut sisään!";
  }
?>

</html>