import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Spielfeld extends JPanel{


    SpielOberflaeche dieSpielOberflaeche;
    private JButton[][] spielfeld = new JButton[8][10];

    public Spielfeld(SpielOberflaeche dieSpielOberflaeche) {
        
        this.dieSpielOberflaeche = dieSpielOberflaeche;
        setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        
        //Spielfeld Erstellung 
        for(int i = 0; i < 8 ; i++){
            for(int j = 0; j < 10; j++){
                
                JButton spielFeld = new JButton();
                   
                spielFeld.setBackground(Color.gray);
                spielFeld.setFocusable(false);
                spielFeld.setPreferredSize(new Dimension(60,60));
                spielFeld.setBorder(null);
                spielfeld[i][j] = spielFeld;

                final int x = i;
                final int y = j;

                spielFeld.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        clickAufSpielFeld(x, y);
                    }
                });

               add(spielFeld);
            }
        }
        
    }
    public void legeStein(int x, int y, char z) {
        
        spielfeld[x][y].setText(z + "");
        if(z == 'W') {
            spielfeld[x][y].setBackground(Color.white);
            spielfeld[x][y].setForeground(Color.BLACK);
        }
        else {
            spielfeld[x][y].setBackground(Color.black);
            spielfeld[x][y].setForeground(Color.WHITE);
        }
    }
    public void loescheSpielFeld() {
       
          // Spielfeld leeren
          for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[i].length; j++) {
                spielfeld[i][j].setText(""); // Text entfernen
                spielfeld[i][j].setBackground(Color.gray); // Farbe zurücksetzen
            }
        }
    }
    public void clickAufSpielFeld(int x, int y) {
        
        dieSpielOberflaeche.uebernehmeMausClick(x,y);
        
    }
}
