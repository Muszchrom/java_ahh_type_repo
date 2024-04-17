import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Lab5 {
  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      try {
        Lab5 app = new Lab5(2);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    });
  }
  public Lab5(Integer zadanie) {
    switch (zadanie) {
      case 1:
        Zadanie1 z1 = new Zadanie1();
        break;
      case 2:
        Zadanie2 z2 = new Zadanie2();
        break;
      case 3:
        Zadanie3 z3 = new Zadanie3();
        break;
      default:
        break;
    }
  }
}

class Zadanie1 {
  String[][] tab;
  public Zadanie1() {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(new File("./sentences.txt")));
      String line;
      int i = 0;
      // file must be 5 line long (I would not use normal array in such aplication, where the number of lines is not defined)
      this.tab = new String[5][];
      while ((line = reader.readLine()) != null) {
        this.tab[i] = line.split(" ");
        i ++;
      }
      reader.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    for (int i = 0; i<this.tab.length; i++) {
      for (int j=0; j<this.tab[i].length; j++) {
        System.out.println(this.tab[i][j]);
      }
    }
  }
}

class Zadanie2 {
  public Zadanie2() {
    System.out.println(1924 & 255);
    System.out.println(4 & 255);
    System.out.println(255 & 255);
    System.out.println(256 & 255);
  }
}

class Pojazd {
  String nazwa, wlasciciel, sposobPoruszaniaSie;
  int liczbaKol, liczbaMiejsc;
  int[] kolor;

  public void setNazwa(String nazwa) {this.nazwa = nazwa;} 
  public void setWlasciciel(String wlasciciel) {this.wlasciciel = wlasciciel;} 
  public void setSposobPoruszaniaSie(String sposobPoruszaniaSie) {this.sposobPoruszaniaSie = sposobPoruszaniaSie;} 
  public void setLiczbaKol(int liczbaKol) {this.liczbaKol = liczbaKol;} 
  public void setLiczbaMiejsc(int liczbaMiejsc) {this.liczbaMiejsc = liczbaMiejsc;} 
  public void setKolor(int[] kolor) { // int and not byte because byte is +- 127 and the method would be harder to use
    this.kolor = new int[3];
    for (int i=0; i<kolor.length; i++) {
      this.kolor[i] = kolor[i] & 255; // ensure that max color value is 255
    }
  } 

  public String getNazwa() {return this.nazwa;} 
  public String getWlasciciel() {return this.wlasciciel;} 
  public String getSposobPoruszaniaSie() {return this.sposobPoruszaniaSie;} 
  public int getLiczbaKol() {return this.liczbaKol;} 
  public int getLiczbaMiejsc() {return liczbaMiejsc;} 
  public int[] getKolor() {return this.kolor;}

  public void poruszajSie() {
    System.out.println("Poruszam się");
  }

  public void przemaluj() {
    System.out.println("Pojazd został przemalowany");
  }

  public void zmienWlasciciela() {
    System.out.println("Właściciel zmieniony");
  }

  public void zmienKola() {
    System.out.println("Koła zmienione");
  }
}

class Rower extends Pojazd {

}
class Samochod extends Pojazd {
  @Override
  public void zmienKola() {
    System.out.println("Jacht nie ma kół, akcja nie może zakończyć się sukcesem");
  }
  @Override
  public void poruszajSie() {
    System.out.println("Jadę samochodem");
  }
}
class Jacht extends Pojazd {
  @Override
  public void zmienKola() {
    System.out.println("Jacht nie ma kół, akcja nie może zakończyć się sukcesem");
  }
  @Override
  public void poruszajSie() {
    System.out.println("Płynę");
  }
}
class Zadanie3 {
  
}