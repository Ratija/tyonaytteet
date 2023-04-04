

///////// Kuukaudet /////////////
function show_month(month){

  var month_name=new Array(12);
  month_name[0]="Tammikuu"
  month_name[1]="Helmikuu"
  month_name[2]="Maaliskuu"
  month_name[3]="Huhtikuu"
  month_name[4]="Toukokuu"
  month_name[5]="Kesäkuu"
  month_name[6]="Heinäkuu"
  month_name[7]="Elokuu"
  month_name[8]="Syyskuu"
  month_name[9]="Lokakuu"
  month_name[10]="Marraskuu"
  month_name[11]="Joulukuu"
  
  return month_name[month];
  
  }
  
  
  //// Muuttujat /////////////
  function cal(chm,chy,month,year,bt,sub){
  month=month + chm;
  year=year + chy;
  dt=new Date(year, month, 01);// Päiväys
  var year=dt.getFullYear(); // Kuluva vuosi
  var display_month=dt.getMonth();
  var return_month=display_month +1; 
  var display_month_name=show_month(display_month);
  var first_day=dt.getDay(); // Kuukauden ensimmäinen päivä
  
  dt.setMonth(month+1,0); // lisää vierekkäiset kuukaudet
  var last_date=dt.getDate(); // Kuukauden viimeinen päivä

  
  var dy=1; // Aloituspäivä
  
  // Linkki kuluvalle kuuakaudelle ja vuodelle // 
  var str1="<td><a href=# onclick=show_cal(0,-1," + display_month + "," + year + ",'" + bt + "','" + sub + "');><< </a> </td><td>   <a href=# onclick=show_cal(-1,0," + display_month + "," + year + ",'" + bt + "','"+ sub + "');><</a> </td><td> "+ display_month_name +"</td><td> " + year + " </td><td align=right><a href=# onclick=show_cal(1,0," + display_month + "," + year + ",'" + bt +"','" + sub + "');>></a> </td><td> <a href=# onclick=show_cal(0,1," + display_month + "," + year + ",'" + bt +"','" + sub + "');>>></a></td>";

  
  // Näyttää kalenterin rungon ////
  var str = '';
  str = "<table class='main'><tr> " + str1 + "  <td  align=right>";
  str += " <a href=# onclick=\"close_cal('" + sub +"' );\";>X</a></td></tr>";  // Lisää sulkemis-napin
  str +="<tr><th>Su</th><th>Ma</th><th>Ti</th><th>Ke</th>";
  str +="<th>To</th><th>Pe</th><th>La</th></tr>";
  
  for(i=0;i<=41;i++){
  if((i%7)==0){str = str + "</tr><tr>";} // Lisää uuden rivin kun viikko loppuu
  if((i>= first_day) && (dy<= last_date)){
  //// Näyttää päivän ///
  
  str = str + "<td id='"+ dy +"'><a href=# onclick=return_value("+ dy + "," + return_month + "," + year + ",'"+ bt +"','" + sub + "');> "+ dy +"</a></td>";
  dy=dy+1;

  }else {str = str + "<td>*</td>";} // Lisää tyhjiä päiviä
  } 
  
  str = str + "</tr></table>";

  return str; 
  }
  
  function show_cal(chm, chy,month,year,bt,sub) {
  document.getElementById(sub).innerHTML = cal(chm,chy,month,year,bt,sub);
  document.getElementById(sub).style.display = 'inline';
  }
  //// Sulkee kalenterin ///////////
  function close_cal(sub) {
  document.getElementById(sub).style.display = 'none';
  }
  // 
  function start_cal(bt,sub) {
  var dt_object=new Date();
  var month=dt_object.getMonth();
  var year=dt_object.getFullYear();
  show_cal(0,0,month,year,bt,sub);
   }
  
  function return_value(dt,month,year,bt,sub){
  document.getElementById(bt).value=dt + '/' +month + '/' + year   ;
  close_cal(sub);
  
   }

   function arvioitu() {
    // Datan syöte kentästä toiseen
    const dateInput = document.getElementById('t2');
    const resultInput = document.getElementById('t3');

    // Päiväysarvon otto
    const dateValue = dateInput.value;

    // Manuaalinen käsittely stringille
    const [day, month, year] = dateValue.split('/');
    const date = new Date(`${year}-${month}-${day}`);
    date.setDate(date.getDate() + 30);

    // Arvioitu päiväys muotoon"pp/kk/vvvv"
    const resultValue = ('0' + date.getDate()).slice(-2) + '/' + ('0' + (date.getMonth() + 1)).slice(-2) + '/' + date.getFullYear();

    // Arvon syöttö kenttään
    resultInput.value = resultValue;
}



  

  
  