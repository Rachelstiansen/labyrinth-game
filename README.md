## Beskrivelse av spillet

Appen er et labyrint-spill hvor du som spiller navigerer deg gjennom en labyrint fra
start til mål ved bruk av piltastene på tastaturet. Labyrinten leses fra en tekstfil hvor # er brukt
for å indikere en vegg og fri bane indikeres av et mellomrom. Spilleren kommer i mål når man
har kommet ut som indikeres ved F i tekstfilen. Appen tar tiden på hvor lang tid spilleren bruker
på å komme seg ut av labyrinten og når spillet er over kan spilleren skrive inn et navn som
lagres sammen med tiden brukt i en highscore-liste.
Appen består av 7 klasser. Klassen Player.java holder styr på spillerens posisjon og
implementerer interfacet Movable.java. LabyrinthGame.java holder styr på labyrintstrukturen,
hvor spilleren starter, sjekker for kollisjon med vegg og om spilleren er i mål.
LabyrinthController.java er kontrolleren og LabyrinthApp.java er view-filen som laster xml-filen.
Klassen LabyrinthFileHandler.java håndterer fillesing og filskriving av labyrinten og highscore.
Det ble også opprettet en egen Highscore.java-klasse for å representere en highscore-entry i
spillet.
