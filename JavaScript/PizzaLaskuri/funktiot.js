
let counter = 0;

function Laskepizzanhinta() {
    let hinta = 0;
    let pizzateksti = "";

    if (document.getElementById("normaali").checked) {
        hinta = 5;
        pizzateksti = "normaali";
    } else if (document.getElementById("perhe").checked) {
        hinta = 7;
        pizzateksti = "perhe";
    }

    if (document.getElementById("kinkku").checked) {
        hinta += 0.7;
        pizzateksti += ",kinkku";
    }

    if (document.getElementById("ananas").checked) {
        hinta += 0.6;
        pizzateksti += ",ananas";
    }

    if (document.getElementById("tonnikala").checked) {
        hinta += 0.5;
        pizzateksti += ",tonnikala";
    }

    if (document.getElementById("herkkusieni").checked) {
        hinta += 0.5;
        pizzateksti += ",herkkusieni";
    }

    if (document.getElementById("oliivi").checked) {
        hinta += 0.5;
        pizzateksti += ",oliivi";
    }

    if (document.getElementById("lisajuusto").checked) {
        hinta += 0.5;
        pizzateksti += ",lisajuusto";
    }

    document.getElementById("txtHinta").value = hinta;
    document.getElementById("hiddenpizzateksti").value = pizzateksti;

}

// Luodaan ostoskori 
function Tilaa() {
    let maara = parseInt(document.getElementById("txtMaara").value);

    let hinta = parseFloat(document.getElementById("txtHinta").value);

    let pizzateksti = document.getElementById("hiddenpizzateksti").value;


    //////////////////////////////////////
    //          OSTOSKORIN KENTÄT       //
    //////////////////////////////////////

    // Rivit ostoskoriin

    ok = document.getElementById("ostoskori");
    // teksti-tagi
    let tesktirivi = document.createElement("p");

    //Täytteet
    let taytteet = document.createElement("input");
    taytteet.type = "text";
    taytteet.value = pizzateksti;
    taytteet.size = 20;

    // + painike
    let plusbutton = document.createElement("input");
    plusbutton.type = "button";
    plusbutton.style.marginTop = '0px 10px';
    plusbutton.setAttribute('id', 'plus' + counter);
    plusbutton.value = "+";
    plusbutton.setAttribute('onclick', 'lisaaPizza(this.id)');

    // Pizzamäärä 
    let pmaara = document.createElement("input");
    pmaara.type = "number";
    pmaara.name = "txtmaara";
    pmaara.setAttribute('id', 'maararivi' + counter);
    pmaara.readOnly = true;
    pmaara.value = maara;

    // - painike
    let miinusbutton = document.createElement("input");
    miinusbutton.type = "button";
    miinusbutton.style.marginTop = '0px 10px';
    miinusbutton.setAttribute('id', 'miinus' + counter);
    miinusbutton.value = "-";
    miinusbutton.setAttribute('onclick', 'poistaPizza(this.id)');

    // Pizzan hinta
    let phinta = document.createElement("input");
    phinta.type = "text";
    phinta.name = "txthinta";
    phinta.id = 'txthinta' + counter;
    phinta.size = 3;
    phinta.readOnly = true;
    phinta.class = "numero";
    phinta.value = hinta;

    // Rivin summa yhteensä
    let riviyhteensa = document.createElement("input");
    riviyhteensa.type = "text";
    riviyhteensa.name = "txtRiviyhteensa";
    riviyhteensa.id = "txtRiviyhteensa" + counter;
    riviyhteensa.size = 3;
    riviyhteensa.readOnly = true;
    riviyhteensa.class = "numero";
    riviyhteensa.value = maara*hinta;

    // X poista rivi
    let poistarivi = document.createElement("input");
    poistarivi.type = "button";
    poistarivi.class = "painikkeet";
    poistarivi.value = "X";
    poistarivi.setAttribute('onclick', 'poistaRivi(this)');

    tesktirivi.appendChild(taytteet);
    tesktirivi.appendChild(plusbutton);
    tesktirivi.appendChild(pmaara);
    tesktirivi.appendChild(miinusbutton);
    tesktirivi.appendChild(phinta);
    tesktirivi.appendChild(riviyhteensa);
    tesktirivi.appendChild(poistarivi);
    ok.appendChild(tesktirivi);

    laskeYhteen();
    counter++;
    document.getElementById("hiddenpizzateksti").value = "";
}


//////////////////////////////////////////////////
//              OSTOSKORIN FUNKTIOT             //
//////////////////////////////////////////////////
function laskeYhteen() {
      
    let rivisumma = 0;
    let kaikkiyhteensa = 0;

            // Hakee ostoskorin rivielementit
    let rivit = document.querySelectorAll('#ostoskori p');

    for (let i = 0; i < rivit.length; i++) {
        let maara = parseInt(rivit[i].querySelector('[name="txtmaara"]').value);
        let hinta = parseFloat(rivit[i].querySelector('[name="txthinta"]').value);
        let riviyhteensa = maara * hinta;
        rivit[i].querySelector('[name="txtRiviyhteensa"]').value = riviyhteensa.toFixed(2);

        rivisumma += riviyhteensa;
    }
    kaikkiyhteensa = rivisumma.toFixed(2);
    document.getElementById('txtKaikkiyhteensa').value = kaikkiyhteensa;
 }


function lisaaPizza(input) {
    let mask = /[0-9]/g; // vain luvut 0-9
    let numero = input.match(mask);

    // haetaan napin vieressä olevas määrä ja kasvatetaan yhdellä
    let maara = parseInt(document.getElementById('maararivi' + numero).value);
    maara++;

    // Asetetaan tuotteiden uusi määrä
    document.getElementById('maararivi' + numero).value = maara;
    laskeYhteen();

}

function poistaPizza(input) {
    let mask = /[0-9]/g; // vain luvut 0-9
    let numero = input.match(mask);

    // haetaan - napin vierestä määrä ja vähennetään yhdellä
    let maara = parseInt(document.getElementById('maararivi' + numero).value);
    if (maara >0) {
        maara--;
    } else {
        return;
    }

    // asetetaan tuotteiden uusi määrä
    document.getElementById('maararivi' + numero).value = maara;
    laskeYhteen();
}

function poistaRivi(input) {
    let ok = document.getElementById('ostoskori');
    ok.removeChild(input.parentNode);
    counter--;
    laskeYhteen();
}

////////////////////////////////////////////////////////////////////////////////////////

// Kenttien tyhjennys    

const Tyhjenna = () => {
    document.getElementById("normaali").checked = false;
    document.getElementById("perhe").checked = false;
    document.getElementById("kinkku").checked = false;
    document.getElementById("ananas").checked = false;
    document.getElementById("tonnikala").checked = false;
    document.getElementById("herkkusieni").checked = false;
    document.getElementById("oliivi").checked = false;
    document.getElementById("lisajuusto").checked = false;
    document.getElementById("txtMaara").value = 1;
    document.getElementById("txtHinta").value = "";
    document.getElementById("hiddenpizzateksti").value = "";

    // Palauta fokus määrä-kenttään
    document.getElementById("txtMaara").focus();

};
















