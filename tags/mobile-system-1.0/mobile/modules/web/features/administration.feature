# language: sv
Egenskap: Administratörssidor
  Som administratör
  vill jag kunna administera skrivarna

  Scenario: Se skrivarna
    Givet att jag är på admin sidan
    Så ska jag se 3 skrivare
    
  Scenario: Se QR kod
	Givet att jag är på admin sidan
	När jag klickar på QR länken
	Så ska jag se en QR kod