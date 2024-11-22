import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;
import javax.swing.Timer;

public class SpielSteuerung {

    //Objekte
    SpielOberflaeche dieSpielOberflaeche;
    SpielDaten       dieSpielDaten;

    //Variablen
    char z; // Aktueller Spieler
    boolean gameRunning = false;

    // Timer-Logik
    private Timer timerWeiss;
    private Timer timerSchwarz;
    private int zeitWeiss = 180; // 3 Minuten in Sekunden
    private int zeitSchwarz = 180;// 3 Minuten in Sekunden


    public SpielSteuerung() {
       
        dieSpielOberflaeche = new SpielOberflaeche(this);
        dieSpielDaten       = new SpielDaten();
    
    
    }
    private char gibZufallsFarbe() {
        double random = Math.random();
        if(random > 0.5){
            z = 'W';
        }
        else{
            z = 'S';
        }
        return z;
    }
    private void wechsleSpieler() {
        
        if(z == 'W'){
            z = 'S';
            starteTimerSchwartz();
        }
        else{
            z = 'W';
            starteTimerWeiss();
        }
    }
    public void bearbeiteMausClick(int x, int y) {
       
        char FeldStatus = dieSpielDaten.leseZeichen(x,y);
        

        //Überprufen ob das Feld leer ist
        if(FeldStatus == 'F' && gameRunning == true){
            dieSpielDaten.schreibeZeichen(x, y, z);
            dieSpielOberflaeche.legeSteinAufSpielFeld(x, y, z);
           
            //Protokoll Aktualisieren
            dieSpielOberflaeche.aktualisiereSpielProtokoll("Spieler " + (z == 'W' ? "Weiß" : "Schwarz") + " setzt auf (" + x + ", " + y + ").");
            wechsleSpieler();
            
            if( z == 'W' ){
                
                 dieSpielOberflaeche.setAusgabeText(0);
                 
              } else {
                 dieSpielOberflaeche.setAusgabeText(1);
                
                }
        }
        //Überprüfen, Ob ein Spieler gewonnen hat
        if(dieSpielDaten.gibMaxLinienLaenge(x, y) == 5 ) {
            gameRunning = false;
            if (timerWeiss != null) timerWeiss.stop();
            if (timerSchwarz != null) timerSchwarz.stop();
            //int MaxCount = dieSpielDaten.gibMaxLinienLaenge(x,y);
            
            //Überprüfen wer gewonnen hat
            if( z == 'W') {
            dieSpielOberflaeche.setAusgabeText(4);
            }else{
                if(  z == 'S')
                dieSpielOberflaeche.setAusgabeText(3);
            }
        }
    
    }  
    public int gibZufallsZahl(int min, int max) {
        
        Random random  = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
    public void starteSpiel() {
    
        gameRunning = true;

        int x = gibZufallsZahl(0, 7);
        int y = gibZufallsZahl(0, 7);
        //System.out.println(x);
        //System.out.println(y);
        z = gibZufallsFarbe();
        dieSpielOberflaeche.setAusgabeText( z == 'W' ? 0 : 1); 
        if(z == 'W') {
            starteTimerWeiss();
        } else {
            starteTimerSchwartz();
        }
        bearbeiteMausClick(x, y);
        
    }
    public void starteTimerWeiss () {

        if(timerSchwarz != null) {
            timerSchwarz.stop();
        }
        timerWeiss = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (zeitWeiss > 0) {
                    zeitWeiss--;
                    dieSpielOberflaeche.aktualisiereTimer('W', zeitWeiss / 60, zeitWeiss % 60);
                 } else {
                     ((Timer) e.getSource()).stop();
                     gameRunning = false;
                     dieSpielOberflaeche.setAusgabeText(4); // Schwarz gewinnt
                 }
              }
         });
            timerWeiss.start();
    }
    public void starteTimerSchwartz() {

        if(timerWeiss != null) {
            timerWeiss.stop();
        }
        timerSchwarz = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (zeitSchwarz > 0) {
                    zeitSchwarz--;
                    dieSpielOberflaeche.aktualisiereTimer('S', zeitSchwarz / 60, zeitSchwarz % 60);
                } else {
                    ((Timer) e.getSource()).stop();
                    gameRunning = false;
                    dieSpielOberflaeche.setAusgabeText(3); // Weiß gewinnt
                }
            }
        });
        timerSchwarz.start();
    }
    public void resetSpiel() {

        dieSpielDaten.loescheDaten();          // Datenstruktur zurücksetzen
        dieSpielOberflaeche.loescheSpielFeld();// Spielfeld leeren
        
        zeitWeiss = 180;   //Timer Weiss Zurücksetzen
        zeitSchwarz = 180; //Timer Schwatz Zurücksetzen

        if (timerWeiss != null) timerWeiss.stop();
        if (timerSchwarz != null) timerSchwarz.stop();
        
        // Timeranzeige aktualisieren
        dieSpielOberflaeche.aktualisiereTimer('W', zeitWeiss / 60, zeitWeiss % 60);
        dieSpielOberflaeche.aktualisiereTimer('S', zeitSchwarz / 60, zeitSchwarz % 60);

        dieSpielOberflaeche.leereProtokoll(); // Protokoll leeren

        starteSpiel();                         // Spiel neu starten
        
    }
    public static void main(String[] args) {
        
      SpielSteuerung dieSpielSteuerung = new SpielSteuerung();
      dieSpielSteuerung.starteSpiel();
    }
}
