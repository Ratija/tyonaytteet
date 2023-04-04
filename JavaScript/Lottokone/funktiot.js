    // Globaalit muuttujat
var valitutNumerot = [];
var tiedostoon = [];
var tiedosto = [];

function load() {
  var taulu = document.getElementById("taulu");
  // Silmukka rivien luomiseksi
  for (var i = 1; i < 8; i++) {
    var rivi = document.createElement("TR");
    // jokaiselle riville oma id
    rivi.setAttribute("id", "rivi" + i.toString());
    taulu.appendChild(rivi);
  }
  // solut ja numerot riveihin
  var rivinumero = 1;
  for (var j = 1; j < 40; j++) {
    var numero = document.createElement("TD");
    numero.setAttribute("id", "numero" + j.toString());
    // Viittaus soluun numeroa valittaessa
    numero.setAttribute("onclick", "valitseNumero(this)");
    // numeroitten lisäys soluihin
    numero.appendChild(document.createTextNode(j.toString()));
    var rivi = document.getElementById("rivi" + rivinumero);
    rivi.appendChild(numero);
    // Jos rivi jaollinen kuudella, lisätään uusi rivi
    if (j % 6 == 0) rivinumero++;
  }
  // Solut valituille lottonumeroille
  var valitutNumerotTaulu = document.getElementById("valitutNumerot");
  for (var k = 1; k < 8; k++) {
    var paikka = document.createElement("TD");
    paikka.setAttribute("id", "valittuNumero" + k.toString());
    paikka.setAttribute("onclick", "poistaNumero(this)");
    valitutNumerotTaulu.appendChild(paikka);
  }
}
// Yhden numeron poisto
function poistaNumero(id) {
  // Solun tarkistus
  if (id.innerHTML != "") {
    var index = valitutNumerot.indexOf(parseInt(id.innerHTML));
    var numero = valitutNumerot[index];
    // splice ottaa parametreina mistä kohtaa aloitetaan poistamaan, ja kuinka monta alkiota poistetaan.
    if (index > -1) valitutNumerot.splice(index, 1);
    // etsii numerotaulusta vastaavam numero, ja palauttaa alkutilanteeseen
    var varjattyNumero = document.getElementById("numero" + numero.toString());
    if (varjattyNumero.style.backgroundColor == "red") varjattyNumero.style.backgroundColor = "white";
    // Siirtyminen pykälällä vasemmalle, ettei jää tyhjiä soluja
    for (var i = index + 1; i < 7; i++) {
      var entinen = document.getElementById("valittuNumero" + i.toString());
      var uusi = document.getElementById("valittuNumero" + (i + 1).toString())
      entinen.style.backgroundColor = uusi.style.backgroundColor;
      entinen.innerHTML = uusi.innerHTML;
      uusi.innerHTML = "";
    }
    // Viimeisen solun tyhjennys
    document.getElementById("valittuNumero7").innerHTML = "";
    document.getElementById("valittuNumero7").style.backgroundColor = "white";
  }
}
// Numeroittenvalinta
function valitseNumero(id) {
  // Ilmoitus jos liikaa numeroita
  if (valitutNumerot.length >= 7) alert("Olet jo valinnut 7 numeroa!");
  // Dublikaattien virheilmoitus
  else if (contains(parseInt(id.innerHTML), valitutNumerot)) alert("Olet jo valinnut tämän numeron!");
  else {
    // Lisää valitun numeron taulukkoon ja maalaa sen punaiseksi
    valitutNumerot.push(parseInt(id.innerHTML));
    id.style.backgroundColor = "red";
    document.getElementById("valittuNumero" + valitutNumerot.length.toString()).innerHTML = id.innerHTML;
  }
}
// Random-arvonta
function arvo() {
  if (valitutNumerot.length < 7) alert("Olet valinnut liian vähän numeroita!");
  else {
    var numerot = [];
    var montaOikein = 0;

    while (numerot.length < 7) {
      var uusinumero = randomInt(39);
      if (numerot.indexOf(uusinumero) == -1) {
        numerot.push(uusinumero);
      }
    }
    // tallentaa numerotiedot fileen
    tiedostoon = numerot.join(", ") + "\n";
    tiedosto += tiedostoon;
   
    // Pyyhkii vanhat numerot pois
    for (var i = 1; i < 40; i++) {
      document.getElementById("numero" + i.toString()).style.backgroundColor = "white";
    }
    // Merkkaa oikeat numerot vihreiksi
    for (var oikeaNumero in numerot) {
      document.getElementById("numero" + numerot[oikeaNumero].toString()).style.backgroundColor = "lightgreen";
    }
    // Tyhjentää arvotun rivin värit ennen uutta arvontaa
    for (var k = 1; k <= valitutNumerot.length; k++) {
      document.getElementById("valittuNumero" + k.toString()).style.backgroundColor = "white";
    }
    // Käy läpi silmukassa valitut numerot, ja tarkistaa osumat
    for (var j = 0; j < valitutNumerot.length; j++) {
      if (contains(valitutNumerot[j], numerot)) {
        document.getElementById("valittuNumero" + (j + 1).toString()).style.backgroundColor = "lightgreen";
        montaOikein++;
      }
    }
    // Tuloksen printtaus
    document.getElementById("tulos").innerHTML = "Sait " + montaOikein.toString() + " oikein!";
  }
  progressBar();
  //tallennaTiedostoon();
}

// Tyhjentää valinnat
function tyhjenna() {
  for (var i = 1; i < 8; i++) {
    var paikka = document.getElementById("valittuNumero" + i.toString());
    paikka.innerHTML = "";
    paikka.style.backgroundColor = "white";
  }
  valitutNumerot.length = 0;

  for (var j = 1; j < 40; j++) {
    paikka = document.getElementById("numero" + j.toString());
    if ((paikka.style.backgroundColor == "lightgreen") || (paikka.style.backgroundColor == "red")) paikka.style.backgroundColor = "white";
  }
  document.getElementById("tulos").innerHTML = ""
}

// Dublikaattien tarkistus
function contains(obj, array) {
  for (var i = 0; i < array.length; i++) {
    if (array[i] == obj) return true;
  }
  return false;
}
function randomInt(max) {
  return Math.floor((Math.random() * max) + 1);
}
// Valittujen numeroitten taustavärin vaihto
function vaihdaVari() {

  var cells = document.getElementsByTagName("TD");
  for (var i = 0; i < cells.length; i++) {
    if (cells[i].style.backgroundColor == "red") {
      cells[i].style.backgroundColor = "white";
    }
    else if (cells[i].style.backgroundColor == "white") {
      cells[i].style.backgroundColor = "yellow";
    }
    else if (cells[i].style.backgroundColor == "yellow") {
      cells[i].style.backgroundColor = "blue";
    }
    else if (cells[i].style.backgroundColor == "blue") {
      cells[i].style.backgroundColor = "red";
    }
  }
}
// Rivien lottous massana
function massaLotto() {
  // Elementti jonne rivit viedään
  var randomNumberElement = document.getElementById("random");
  var x = document.getElementsByName("massa")[0].value

  for (var i = 0; i < x; i++) {
    var line = document.createElement("p");
    var numerot = [];
    while (numerot.length < 7) {
      var randomNum = Math.floor(Math.random() * 39) + 1;
      if (!numerot.includes(randomNum)) {
        numerot.push(randomNum);
      }
    }
    line.innerHTML = numerot.join(", ");
    randomNumberElement.appendChild(line);
     // tallentaa numerotiedot fileen
     tiedostoon = numerot.join(", ") + "\n";
     tiedosto += tiedostoon;
  }
  progressBar();
  }

// Suorituspalkki
var i = 0;
function progressBar() {
  if (i == 0) {
    i = 1;
    var elem = document.getElementById("myBar");
    var width = 10;
    var id = setInterval(frame, 5);
    function frame() {
      if (width >= 100) {
        clearInterval(id);
        i = 0;
      } else {
        width++;
        elem.style.width = width + "%";
        elem.innerHTML = width + "%";
      }
    }
  }
}
// Numeroitten lataus tiedostona
function tallennaTiedostoon() {
  var blob = new Blob([tiedosto], {type: "text/plain;charset=utf-8"});
  saveAs(blob, "lottonumerot.txt");
}





