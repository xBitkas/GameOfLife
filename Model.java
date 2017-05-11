package GameOfLifeNeu;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;

/**
 * Created by Markus on 10.05.17.
 */
public class Model extends Observable {

  int hoehe = 15;
  int breite = 15;
  public static JButton[][] zelle;


  public Model() {

    //View als observer hinzufügen
    addObserver(new View());

    //JButtonarray erstellen
    zelle = new JButton[breite][hoehe];

    //Array zeichnen
    int i = 0;
    int j = 0;

    for (i = 0; i < zelle.length; i++) {
      for (j = 0; j < zelle.length; j++) {
        zelle[j][i] = new JButton();
        zelle[j][i].setBackground(Color.white);


      }
    }

    notifyObservers();
  }


  public static int getZelleLength() {

    return zelle.length;


  }


}













