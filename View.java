package GameOfLifeNeu; /**
 * Created by Markus on 10.05.17.
 */

import Abgabe7.HelloRunnable;
import GameOfLifeNeu.Konsole;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class View extends JApplet implements Observer {

  private JDesktopPane desk; // Ersatz fuer Standard-ContentPane

  public View() { // Konstruktor
    desk = new JDesktopPane(); // neue DesktopPane
    desk.setDesktopManager(new DefaultDesktopManager()); // mit neuem Manager
    setContentPane(desk); // als neue ContentPane
  } // end Konstruktor

  public void addChild(JInternalFrame child, int x, int y) { // Hinzufuegen
    child.setLocation(x, y); // Ort und
    child.setSize(200, 150); // Groesse setzen
    child.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE); // Schiessoperation
    desk.add(child); // Kindfenster einfuegen
    child.setVisible(true); // und sichtbar machen
  } // end addChild

  public static void main(String[] args) {
    View desktop = new View(); // Hauptfenster erzeugen

    desktop.addChild(new ChildFrame(desktop), 5, 5); // Ein Kindfenster einfuegen
    Konsole.run(desktop, 850, 850); // Konsolenstart


  } // end main

  @Override
  public void update(Observable o, Object arg) {

  }



} // end class DesktopFrameTest

class ChildFrame extends JInternalFrame implements  ActionListener{ // Klasse fuer Kindfenster

  static JButton[][] zelle;
  static JButton[][] gliderArray;
  int breite = 10;
  int hoehe = 10;
  Thread game;

  static int nr = -1, xpos = 30, ypos = 30; // statische Variablen
  static final Color[] col = {Color.red, Color.green}; // verfuegbare Farben ...
  View mydesk; // Referenz auf Hauptfenster

  public ChildFrame(View dft) { // Konstruktor
    super("Kind " + (++nr), true, true); // vergroesserbar, schliessbar
    setBackground(col[nr % col.length]); // Start-Farbe
    mydesk = dft; // Hauptfenster merken

    this.setPreferredSize(new Dimension(750,750));

    Container cp = getContentPane(); // Fenster-Container



    JMenuBar menuBar = new JMenuBar();

    JMenu spiel = new JMenu("Spiel");
    menuBar.add(spiel);

    JMenuItem spielStarten = new JMenuItem("Spiel Starten");
    spiel.add(spielStarten);
    spielStarten.addActionListener(this);

    //Hinzufügen von Menus
    JMenu formen = new JMenu("Formen");
    menuBar.add(formen);

    //Hinzufügen von MenuItem Glider
    JMenuItem glider = new JMenuItem("Glider einfügen");
    formen.add(glider);
    // AL fuer Fensterknopf
    glider.addActionListener(e -> {

      //Glider

        //Startzustand
        zelle[1][0].setBackground(Color.green);
        zelle[2][1].setBackground(Color.green);
        zelle[2][2].setBackground(Color.green);
        zelle[1][2].setBackground(Color.green);
        zelle[0][2].setBackground(Color.green);
        //TODO Muss ins model synchronisiert werden
    });

    //Seperator
    formen.addSeparator();

    //menubar in den Frame setzen
    this.setJMenuBar(menuBar);

    breite = 15;
    hoehe = 15;

    //Layoutmanager
    this.setVisible(true);

    //Spielfeld erstellen
    JPanel p = new JPanel(new GridLayout(breite, hoehe));

    this.add(p);

    //JButtonarray erstellen
    zelle = new JButton[breite][hoehe];

    //Array zeichnen
    int i = 0;
    int j = 0;

    for (i = 0; i < zelle.length; i++) {
      for (j = 0; j < zelle.length; j++) {
        zelle[j][i] = new JButton();
        zelle[j][i].setBackground(Color.white);
        zelle[j][i].addActionListener(this);
        p.add(zelle[j][i]);

      }
    }

    p.add(zelle[breite - 1][hoehe - 1]);

    game = new Thread();

    setIconifiable(true);
    setMaximizable(true);
  }


  @Override
  public void actionPerformed(ActionEvent e) {

    //Events abfangen und Quelle identifizieren
    Object source = e.getSource();

    //JButton event
    if (source.getClass() == JButton.class) {
      JButton srcButton = (JButton) e.getSource();

      if (srcButton.getBackground().equals(Color.white)) {
        srcButton.setBackground(Color.green);
      }

    }
    //JMenuItem event
    else if (source.getClass() == JMenuItem.class) {
      JMenuItem srcItem = (JMenuItem) e.getSource();

      if (srcItem.getActionCommand().equals("Spiel Starten")) {
        (new Thread(new HelloRunnable())).start();


      }

    }




  }
}



       /*
        JButton jb = new JButton("Neue Farbe"); // Knopf fuer Farbe
        cp.add(jb); // einfuegen
        jb.addActionListener(new ActionListener() { // AL fuer Farbknopf
            int i = nr % col.length;

            public void actionPerformed(ActionEvent e) {
                i = (i + 1) % col.length;
                setBackground(col[i]);
            }
        });
        */


        /*
        jb = new JButton("Neues Fenster"); // Knopf fuer neues Fenster
        cp.add(jb); // einfuegen
        jb.addActionListener(new ActionListener() { // AL fuer Fensterknopf
            public void actionPerformed(ActionEvent e) {
                mydesk.addChild(new ChildFrame(mydesk), xpos, ypos);
                xpos += 20;
                ypos += 20;
            }
        });
        */