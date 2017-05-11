package GameOfLifeNeu;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Created by Markus on 10.05.17.
 */


public class Controller implements Runnable {

  public Controller() {

  }



  //checkt die zellennachbarn im 9x9 feld
  static int checkNeighbour(int i, int j) {

    int nachbar = 0;

    //oben links
    if (Model.zelle[(i - 1 % Model.zelle.length + Model.zelle.length) % Model.zelle.length][
        (j - 1 % Model.zelle[0].length + Model.zelle[0].length) % Model.zelle[0].length]
        .getBackground()
        .equals(Color.green)) {
      nachbar++;
    }
    //mitte links
    if (Model.zelle[(i - 1 % Model.zelle.length + Model.zelle.length) % Model.zelle.length][
        (j % Model.zelle[0].length + Model.zelle[0].length) % Model.zelle[0].length].getBackground()
        .equals(Color.green)) {
      nachbar++;
    }
    //unten links
    if (Model.zelle[(i - 1 % Model.zelle.length + Model.zelle.length) % Model.zelle.length][
        (j + 1 % Model.zelle[0].length + Model.zelle[0].length) % Model.zelle[0].length]
        .getBackground()
        .equals(Color.green)) {
      nachbar++;
    }
    //oben mitte
    if (Model.zelle[(i % Model.zelle.length + Model.zelle.length) % Model.zelle.length][
        (j - 1 % Model.zelle[0].length + Model.zelle[0].length) % Model.zelle[0].length]
        .getBackground()
        .equals(Color.green)) {
      nachbar++;
    }
    //unten mitte
    if (Model.zelle[(i % Model.zelle.length + Model.zelle.length) % Model.zelle.length][
        (j + 1 % Model.zelle[0].length + Model.zelle[0].length) % Model.zelle[0].length]
        .getBackground()
        .equals(Color.green)) {
      nachbar++;
    }
    //oben rechts
    if (Model.zelle[(i + 1 % Model.zelle.length + Model.zelle.length) % Model.zelle.length][
        (j - 1 % Model.zelle[0].length + Model.zelle[0].length) % Model.zelle[0].length]
        .getBackground()
        .equals(Color.green)) {
      nachbar++;
    }
    //rechts mitte
    if (Model.zelle[(i + 1 % Model.zelle.length + Model.zelle.length) % Model.zelle.length][
        (j % Model.zelle[0].length + Model.zelle[0].length) % Model.zelle[0].length].getBackground()
        .equals(Color.green)) {
      nachbar++;
    }
    //rechts unten
    if (Model.zelle[(i + 1 % Model.zelle.length + Model.zelle.length) % Model.zelle.length][
        (j + 1 % Model.zelle[0].length + Model.zelle[0].length) % Model.zelle[0].length]
        .getBackground()
        .equals(Color.green)) {
      nachbar++;
    }
    return nachbar;
  }


  public static void checkEnvironment() {

    //Für Zellen die in der nächsten Generation sterben
    ArrayList<Position> prepareToDie = new ArrayList<>();

    //Für Zellen die in der nächsten Generation geboren werden
    ArrayList<Position> prepareToLive = new ArrayList<>();

    for (int j = 0; j < Model.zelle.length; j++) {
      for (int i = 0; i < Model.zelle.length; i++) {

        int nb = checkNeighbour(i, j);

        //nachbarn überprüfen

        //Lebende Zellen mit weniger als zwei lebenden Nachbarn sterben in der Folgegeneration an Einsamkeit.
        if (Model.zelle[1][j].getBackground().equals(Color.green) && nb < 2) {

          prepareToDie.add(new Position(i, j));

        }

        //Eine tote Zelle mit genau drei lebenden Nachbarn wird in der Folgegeneration neu geboren.
        if (Model.zelle[i][j].getBackground().equals(Color.white) && (nb == 3)) {
          Model.zelle[i][j].setBackground(Color.green);

          prepareToLive.add(new Position(i, j));


        }
        //Eine lebende Zelle mit zwei oder drei lebenden Nachbarn bleibt in der Folgegeneration am Leben.
        if (Model.zelle[i][j].getBackground().equals(Color.green) && (nb == 2 || nb == 3)) {

        }

        //Bei mehr als 3 Nachbarn stirbt die Zelle an Überbevölkerung
        if (Model.zelle[i][j].getBackground().equals(Color.green) && (nb > 3)) {

          //Speichert positionen der Zellen, die in der nächsten generationen sterben

          prepareToDie.add(new Position(i, j));

        }
      }
    }

    //Tötet alle Zellen in der Liste in der nächsten generation
    for (Position p : prepareToDie) {
      Model.zelle[p.i][p.j].setBackground(Color.white);


    }

    //Erschafft alle Zellen in der Liste in der nächsten generation
    for (Position p : prepareToLive) {
      Model.zelle[p.i][p.j].setBackground(Color.green);

    }


  }

//TODO View aktualisieren
  //TODO null im thread beseitigen

  @Override
  public void run() {

        while (true) {
            try {

                checkEnvironment();
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println(e.getMessage() + " " + e.getCause());

                //some code
            }

        }

    }

    public static void main(String args[]) {

  }

}


class Position {

  public int i;
  public int j;

  public Position(int I, int J) {
    i = I;
    j = J;
  }
}



