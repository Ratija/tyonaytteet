<?php
  function haeTietokantaYhteys()
  {
    global $conn;
    if($conn) {
        return $conn;
    }
    else {
        $conn = mysqli_connect('localhost', 'hevostallinsiivooja', 'hevoskuiskaaja123#!', 'elainrekisteri')
        or die('Kantayhteyden muodostus epäonnistui.' );
    }
    return $conn;
  }
?>