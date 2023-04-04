from tkinter import *
import tkinter as tk
from geopy.geocoders import Nominatim
from tkinter import messagebox
from timezonefinder import TimezoneFinder
from datetime import datetime
import requests
import pytz
from PIL import Image, ImageTk
import os
from tktooltip import ToolTip
from gtts import gTTS
import playsound
import folium
from tkinter import Tk, Label

# Kielimääritys äänentoistoon
language = "fi"

# Käyttöliittymän ruutu
ikkuna = Tk()
ikkuna.title("Säähaku")
ikkuna.geometry("900x600+300+200")
ikkuna.resizable(False, False)

                        # Funktiot

# Äänentoisto
def soita_saa():
    playsound.playsound('saa.mp3')  # Ei toimi versiolla 1.3. Asenna 1.2.2

# Sään haku
def haeSaa():
    try:
        kaupunki = textfield.get()
        geolocator = Nominatim(user_agent="geoapiExercises")
        paikka = geolocator.geocode(kaupunki)
        obj = TimezoneFinder()
        result = obj.timezone_at(lng=paikka.longitude, lat=paikka.latitude)
        home = pytz.timezone(result)
        paikallinenaika = datetime.now(home)
        kellonaika = paikallinenaika.strftime("%H:%M")
        kello.config(text=kellonaika)
        nimi.config(text="Kellonaika:")
        dt=datetime.now()
        
# Sää API
        api = ("https://api.openweathermap.org/data/2.5/weather?q=" + kaupunki + "&lang=fi&appid=1f0b87a313b8f80b190285608cbf3272&units=metric")

        json_data = requests.get(api).json()
        kuvaus = json_data["weather"][0]["description"]
        lampo = int(json_data["main"]["temp"])
        tuntuu = int(json_data["main"]["feels_like"])
        ilpaine = json_data["main"]["pressure"]
        kosteusmaara = json_data["main"]["humidity"]
        tuulisuus = json_data["wind"]["speed"]
        suunta = json_data["wind"]["deg"]
        kuva = json_data["weather"][0]["icon"]

# Arvojen asetus json-tiedoista
        ltila.config(text=(lampo, "°")) 
        tuntuma.config(text=(kuvaus, "|",)) # "Tuntuu", "kuin", tuntuu, "°"))        #ASCII 0176
        ltila2.config(text=("Tuntuu", "kuin", tuntuu, "°"))         
        tuuli.config(text=(tuulisuus, "m/s", ",", suunta,"°"))
        kosteus.config(text=(kosteusmaara, "%"))
        paine.config(text=(ilpaine, "hPa"))
        
# Kuvan haku openweatherilta
        img_url = "https://openweathermap.org/img/w/"+ kuva + ".png"
        image = Image.open(requests.get(img_url, stream=True).raw)

# Logon määritys
        resize_image = image.resize((250, 250))
 # Logo
        Logo_image = ImageTk.PhotoImage(resize_image)
        logo = Label(ikkuna, image=Logo_image)
        logo.image = Logo_image # Sailyttää viittauksen logoon. Korjaa ongelman, jossa pythonin roskankerääjä poistaa kuvan. Tkinter widgetin ja Tk:n ristiriita 
        logo.size
        logo.place(x=325, y=165)
        
# Äänentoiston parametrit         
        kaupunki = textfield.get()
        l = ltila.cget("text")
        t = ltila2.cget("text")
        text= "Lämpötila kohteessa" + kaupunki + "on" + l + "/ / / / /" + "Tuulen vaikutuksesta se" + t
        speech= gTTS(text=text, lang=language, slow=False)
        speech.save("saa.mp3")
        ikkuna.after(1000, soita_saa) # 1s paussi, muuten ei saa ääntä asetettua loppuun
             
    except Exception as e:
        messagebox.showerror("Säähaku", "Kaupunkia ei ole!")

# Karttapaikanus            
def Paikanna():
    kaupunki = textfield.get() # Karttaa varten
    try:
        locReq=requests.get('https://ipinfo.io/city/?token=640900fb8a280e')    # Kaupungin haku IP:llä 
        latLng=requests.get('https://ipinfo.io/loc/?token=640900fb8a280e')         # Lat & long haku IP:llä
        latLng=latLng.text.strip()
        [var1,var2]=latLng.split(',')
        global lat
        lat=var1
        global lng
        lng=var2
        
        city=locReq.text.strip()
        textfield.insert(END, city)
        
    except Exception as e:
        print(e)
        
    try:
        m = folium.Map(location = [lat, lng], zoom_start=16)
        folium.Marker(location=[lat,lng], popup =  kaupunki).add_to(m)
        folium.CircleMarker(location=(lat,lng),radius=100, fill_color='blue').add_to(m)
               
 
# kartan tallennus ja avaus selaimeen
        m.save('kartta.html')
        os.system('kartta.html')
    except Exception as e:
        print(e)
    
    
       
# Hakulaatikko
Search_image = PhotoImage(file="haku.png")
myimage = Label(image=Search_image)
myimage.place(x=220, y=20)

textfield = tk.Entry(ikkuna, justify="center", width=17, font=("Verdana", 24, "bold"), bg="grey17", fg="white", border=0)
textfield.place(x=240, y=40)
textfield.focus()

# Suurennuslasi
haku = PhotoImage(file="suurennuslasi.png")
hae = Button(image=haku, borderwidth=0, cursor="hand2", bg="grey17", command=haeSaa)
ToolTip(hae, msg="Hae kaupungin nimellä")
hae.place(x=600, y=32)

# Paikannus
paikannus = PhotoImage(file="paikanna.png")
paikanna= Button(image=paikannus, borderwidth=0, cursor="hand2", bg="grey17", command=Paikanna)
ToolTip(paikanna, msg="Paikanna kartalta")
paikanna.place(x=550, y=36)

# Alalaatikko
Frame_image = PhotoImage(file="laatikko.png")
frame_myimg = Label(image=Frame_image)
frame_myimg.place(x=10, y=450)
frame_myimg.pack(side=BOTTOM)

# Kello
nimi = Label(ikkuna, font=("Verdana", 16, "bold"))
nimi.place(x=380, y=100)
kello = Label(ikkuna, font=("Verdana", 20, "bold"))
kello.place(x=390, y=130)

# Labelit

label1 = Label(ikkuna, text="Tuulee,suunnasta", font=("Verdana", 16, "bold"), fg="white", bg="blue")
label1.place(x=180, y=510)

label1 = Label(ikkuna, text="Kosteus", font=("Verdana", 16, "bold"), fg="white", bg="blue")
label1.place(x=420, y=510)

label1 = Label(ikkuna, text="Ilmanpaine", font=("Verdana", 16, "bold"), fg="white", bg="blue")
label1.place(x=620, y=510)

# lämpötila ja tuntuma
ltila = Label(font=("Verdana", 40, "bold"), fg="red")
ltila.place(x=420, y=410)
tuntuma = Label(font=("Verdana", 16, "bold"))
tuntuma.place(x=350, y=470)
ltila2 = Label(font=("Verdana", 16, "bold"))
ltila2.place(x=600, y=470)


# Haetut arvot
tuuli = Label(text="....", font=("Verdana", 14, "bold"), bg="blue")
tuuli.place(x=180, y=540)
kosteus = Label(text="....", font=("Verdana", 14, "bold"), bg="blue")
kosteus.place(x=420, y=540)
paine = Label(text="....", font=("Verdana", 14, "bold"), bg="blue")
paine.place(x=620, y=540)

ikkuna.mainloop()
