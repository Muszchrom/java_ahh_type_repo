import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
 
public class Lab5 {
  public static void main(String[] args) {
    Lab5 app = new Lab5(1);
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
      // A po Polsku, plik musi mieć 5 linijek, ponieważ lepszym rozwiązaniem byłoby tu użycie ArrayList
      // Ze zwykłymi tablicami jest taki problem, że musiałbym tworzyć nową tablicę co iterację pętli, 
      // żeby móc pomieścić nieznaną liczbę linijek tekstu
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
    System.out.println("Zadanie 2 to poprostu napisane klas");
  }
}
 
class Pojazd {
  String nazwa, wlasciciel, sposobPoruszaniaSie;
  int liczbaKol, liczbaMiejsc;
  int[] kolor;
 
  public Pojazd() {
    setNazwa("Pojazd nie ma nazwy");
    setWlasciciel("Pojazd nie ma wlasciciela");
    setSposobPoruszaniaSie("Jeżdzi");
    setLiczbaKol(4);
    setLiczbaMiejsc(5);
    int[] kolor = new int[3];
    setKolor(kolor);
  }
 
  public Pojazd(String nazwa) {
    setNazwa(nazwa);
    setWlasciciel("Pojazd nie ma wlasciciela");
    setSposobPoruszaniaSie("Jeżdzi");
    setLiczbaKol(4);
    setLiczbaMiejsc(5);
    int[] kolor = new int[3];
    setKolor(kolor);
  }
 
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
 
  public void przemaluj(int[] kolor) {
    setKolor(kolor);
    System.out.println("Pojazd został przemalowany");
  }
 
  public void zmienWlasciciela(String wlasciciel) {
    setWlasciciel(wlasciciel);
    System.out.println("Właściciel zmieniony");
  }
 
  public void zmienKola() {
    System.out.println("Koła zmienione");
  }
}
 
class Rower extends Pojazd {
  public Rower() {
    super();
    setNazwa("Rower nie ma nazwy");
    setWlasciciel("Rower nie ma wlasciciela");
    setLiczbaKol(2);
    setLiczbaMiejsc(1);
  }
  public Rower(String nazwa) {
    super();
    setNazwa(nazwa);
    setWlasciciel("Rower nie ma wlasciciela");
    setLiczbaKol(2);
    setLiczbaMiejsc(1);
  }
  @Override
  public void zmienKola() {
    System.out.println("Koła w rowerze zmienione");
  }
  @Override
  public void poruszajSie() {
    System.out.println("Jadę rowerem");
  }
}
 
class Samochod extends Pojazd {
  public Samochod() {
    super();
    setNazwa("Samochod nie ma nazwy");
    setWlasciciel("Samochod nie ma kol");
  }
  public Samochod(String nazwa) {
    super();
    setNazwa(nazwa);
    setWlasciciel("Samochod nie ma kol");
  }
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
  public Jacht() {
    super();
    setNazwa("Jacht nie ma nazwy");
    setWlasciciel("Jacht nie ma kol");
    setSposobPoruszaniaSie("Pływa");
    setLiczbaKol(0);
  }
  public Jacht(String nazwa) {
    super();
    setNazwa(nazwa);
    setWlasciciel("Jacht nie ma kol");
    setSposobPoruszaniaSie("Pływa");
    setLiczbaKol(0);
  }
 
 
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
  public Zadanie3() {
    Rower rower[] = new Rower[4];
    Samochod samochod[] = new Samochod[4];
    Jacht jacht[] = new Jacht[4];
    int[] kolor = new int[3];
 
    for (int i=0; i<4; i++) rower[i] = new Rower("Rower nr: " + i);
    for (int i=0; i<4; i++) samochod[i] = new Samochod("Rower nr: " + i);
    for (int i=0; i<4; i++) jacht[i] = new Jacht("Rower nr: " + i);
 
    Object[] pojazdy = {
      new Rower("Rower nr 1"), 
      new Samochod("Rower nr 2"),
      new Jacht("Rower nr 3")
    };
 
    System.out.println("Nazwy przed edycja");
    for (int i=0; i<3; i++) System.out.println(((Pojazd) pojazdy[i]).getNazwa());;
    zmienNazwe(pojazdy, "Rower numer 1");
 
    System.out.println("Nazwy przed edycja");
    for (int i=0; i<3; i++) System.out.println(((Pojazd) pojazdy[i]).getNazwa());;
 
    kolor[0] = 123; kolor[1] = 123; kolor[2] = 123;
    przemaluj(rower, kolor);
  }
  // malowanie dla wybranych, zmiana wlasciciela dla wszystkich
 
  // jeden rodzaj rozwiazania, przyjmujemy każde obiekty a później filtrujemy
  private void zmienNazwe(Object[] pojazdy, String nazwa) {
    for (int i=0; i<pojazdy.length; i++) {
      if (pojazdy[i] instanceof Rower) ((Rower) pojazdy[i]).setNazwa(nazwa);
      if (pojazdy[i] instanceof Samochod) ((Samochod) pojazdy[i]).setNazwa(nazwa);
      if (pojazdy[i] instanceof Jacht) ((Jacht) pojazdy[i]).setNazwa(nazwa);
      if (pojazdy[i] instanceof Pojazd) ((Pojazd) pojazdy[i]).setNazwa(nazwa);
    }
  }
 
  // rozwiązanie bazujące na klasie rodzicu
  private void przemaluj(Pojazd[] pojazdy, int[] color){
    for (int i=0; i<pojazdy.length; i++) {
      String colorBefore = colorHelper(pojazdy[i]);
      pojazdy[i].setKolor(color);
      System.out.println("===== Zaczynanie Malowania =====");
      System.out.println("Typ pojazdu: " + pojazdy[i].getClass().getSimpleName());
      System.out.println("Nazwa: " + pojazdy[i].getNazwa());
      System.out.println("Zmieniono kolor z: " + colorBefore + " na " + colorHelper(pojazdy[i]));
      System.out.println("===== Malowanie Zakończone =====");
 
    }
  }
  // funkcja ułatwiająca formatowanie nazw kolorów
  private String colorHelper(Pojazd p) {
    String s = "";
    int[] color = p.getKolor();
    for (int i=0; i<3; i++) {
      s += Integer.toString(color[i]);
      if (i < 2) {
        s += ", ";
      }
    }
    return s;
  }
}