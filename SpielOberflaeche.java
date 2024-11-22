
import javax.swing.*;

public class SpielOberflaeche extends JFrame {

    //Objekte 
    SpielSteuerung dieSpielSteuerung;   
    Spielfeld      dasSpielfeld; 

    //Variabel
    JLabel text = new JLabel();
    JButton resetButton = new JButton("Restart");
    JLabel timerWeiss = new JLabel("Weiß: 03:00");
    JLabel timerSchwarz = new JLabel("Schwarz: 03:00");
    
    //Spiel Protokoll
    JTextArea spielProtokoll = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(spielProtokoll);


    private static final String[] ausgabeText = {
        "Spieler Weiß ist am Zug ",
        "Spieler Schwarz ist am Zug ",
        "Dieses Feld ist bereits belegt! ",
        "Spieler Weiß hat gewonnen! ",
        "Spieler Schwarz hat gewonnen! "
    };

    public SpielOberflaeche(SpielSteuerung diespielSteuerung) {
       
        this.dieSpielSteuerung = diespielSteuerung;
        this.dasSpielfeld = new Spielfeld(this);
        
       //GUI
       setTitle("5 in einer Reihe");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(800,600);
       setLocationRelativeTo(null);
       setLayout(null);
       setResizable(false);

       dasSpielfeld.setBounds(0, 0, 600, 480);
       add(dasSpielfeld);

        //Text Area
        text.setBounds(150, 500, 400, 25);
        
        //Text Area adden zum screen
        add(text);

         // Timer-Anzeigen
         timerWeiss.setBounds(610, 50, 150, 25);
         timerSchwarz.setBounds(610, 100, 150, 25);
         add(timerWeiss);
         add(timerSchwarz);

        // Reset-Button hinzufügen
        resetButton.setBounds(650, 500, 100, 30);
        resetButton.addActionListener(e -> dieSpielSteuerung.resetSpiel());
        resetButton.setFocusable(false);
        add(resetButton);

        //Spiel Protokol
        spielProtokoll.setEditable(false); //Nur lesbar
        spielProtokoll.setLineWrap(true);
        spielProtokoll.setWrapStyleWord(true);
        scrollPane.setBounds(610, 150, 170, 300);
        add(scrollPane);

        
       setVisible(true);       
    }
    
    public void uebernehmeMausClick(int x, int y) {

        dieSpielSteuerung.bearbeiteMausClick(x, y);
    }

    public void legeSteinAufSpielFeld(int x, int y, char z) {
     
        dasSpielfeld.legeStein(x, y, z);
    }

    public void loescheSpielFeld() {
        
        dasSpielfeld.loescheSpielFeld();
    }

    public void setAusgabeText(int index) {
        
        text.setText(ausgabeText[index]);
    }
    // Aktualisiere Timer-Anzeigen
    public void aktualisiereTimer(char z, int minuten, int sekunden) {
        String zeit = String.format("%02d:%02d", minuten, sekunden);
        if (z == 'W') {
            timerWeiss.setText("Weiß: " + zeit);
        } else if (z == 'S') {
            timerSchwarz.setText("Schwarz: " + zeit);
        }
    }
    public void aktualisiereSpielProtokoll(String nachricht) {
        
        spielProtokoll.append(nachricht + "\n");
        spielProtokoll.setCaretPosition(spielProtokoll.getDocument().getLength());
    }
    public void leereProtokoll() {

        //Löschen vom Inhalt des Protokolls
        spielProtokoll.setText(""); 
    }
}
