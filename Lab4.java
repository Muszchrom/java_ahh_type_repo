/* 1. Jakie klasy stosujemy do obsługi odczytu i zapisu plików tekstowych i binarnych?
 * read:  FileReader, FileInputStream
 * write: FileWriter, FileOutputStream
 */

/* 2. Jakie metody z klasy String służą do poszukiwania i dopasowania elementów łańcuchów?
 * charAt, contains, endsWith, indexOf, lastIndexOf, matches(regex)
 */

/* 3. Do czego służy StringBuffer?
 * Wspomaga wykonywanie operacji na stringach, wykonywane metody są szybsze niż klasy String
 * Jeżeli dany String będzie często modyfikowany to lepiej używać właśnie StringBuffer
 */

/* 4. Jakie metody można znaleźć w klasie File?
 * getName, getPath, renameTo, exists, length, delete, mkdir, list
 */

 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 
 public class Lab4 {
   public static void main(String[] args) {
     zadanie3();
   }
 
   /* Napisz program odczytujący z pliku tekstowego zadania.txt wiersze w formacie 
    * LICZBA1 OPERACJA LICZBA2. Operacje dodawania i odejmowania. Program powinien 
    * tworzyć plik wyniki.txt z wierszami w formacie LICZBA1 OPERACJA LICZBA2 = WYNIK 
    * oraz wypisywać wiersze na ekranie. Wykorzystaj do tego metodę concat() klasy String.
    */
   private static void zadanie1() {
     File plik = new File("P:\\code\\java\\ZadaniaZZajec\\file.txt");
     File plikDocelowy = new File("P:\\code\\java\\ZadaniaZZajec\\wyniki.txt");
     try {
       BufferedReader wejscie = new BufferedReader(new FileReader(plik));
       PrintWriter wyjscie = new PrintWriter(new FileWriter(plikDocelowy));
 
       String wiersz = wejscie.readLine();
       while (wiersz != null) {
         String[] s = wiersz.split(" ");
         Integer a = Integer.parseInt(s[0]);
         Integer b = Integer.parseInt(s[2]);
 
         if (s[1].equals("+")) wyjscie.println(wiersz.concat(" = " + (a + b)));
         else if (s[1].equals("-")) wyjscie.println(wiersz + " = " + (a - b));
         else {
           wejscie.close();
           wyjscie.close();
           throw new Exception("File error occured");
         }
         wiersz = wejscie.readLine();
       }
       wejscie.close();
       wyjscie.close();
     } catch (IOException e) {
       System.out.println("An error occured: " + e.getMessage());
     } catch (Exception e) {
       System.out.println("An error occured: " + e.getMessage());
     } 
   }
 
   /* Napisz program pobierający od użytkownika w konsoli IMIĘ, NAZWISKO, ROK studiów i WIEK. 
    * Program powinien tworzyć plik IMIENAZWISKO.txt W pliku powinien być zapisywany wiersz 
    * IMIĘ, NAZWISKO, ROK, WIEK. W razie istnienia pliku powinien być w nim tworzony nowy wiersz. 
    */
   private static void zadanie2() {
     File plikDocelowy = new File("P:\\code\\java\\ZadaniaZZajec\\IMIENAZWISKO.txt");
     try {
       BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       PrintWriter wyjscie = new PrintWriter(new FileOutputStream(plikDocelowy, true));
       System.out.print("Provide: IMIE; NAZWISKO; ROK STUDIOW; WIEK: ");
       String s = reader.readLine();
       String[] sa = s.split(" ");
       if (sa.length != 4) {
         wyjscie.close();
         throw new Exception("Invalid number of params");
       }
       wyjscie.write(s + "\n");
       wyjscie.close();
     } catch (Exception e) {
       System.out.println(e.getMessage());
     }
   }
 
   /* Zapisz instrukcję ćwiczenia w pliku instrukcja.txt Napisz program odczytujący zawartość 
    * pliku i wyszukujący wszystkie zdania, ZACZYNAJĄCE się od „Napisz program”. Zdania 
    * zawierające „Napisz program” w środku zdania nie powinny się tam znaleźć. Zdania 
    * powinny być zapisywane w pliku Napisz program.txt.
    */
   private static void zadanie3() {
     File source = new File("P:\\code\\java\\ZadaniaZZajec\\instrukcja.txt");
     File dest = new File("P:\\code\\java\\ZadaniaZZajec\\program.txt");
     try {
       BufferedReader wejscie = new BufferedReader(new FileReader(source));
       PrintWriter wyjscie = new PrintWriter(new FileWriter(dest));
 
       String wiersz = wejscie.readLine();
       while (wiersz != null) {
         // pierwszy filtr wybierający wiersze, w których są słowa "Napisz program"
         if (wiersz.indexOf("Napisz program") != -1) {
           // Założenie, że każde zdanie poprzedzone jest przez kropkę i spację
           String[] splitted = wiersz.split("[.] ");
           for (int i=0; i<splitted.length; i++) {
             // Sprawdzanie czy "Napisz program jest na początku zdania"
             if (splitted[i].indexOf("Napisz program") == 0) wyjscie.println(splitted[i]);
           }
         }
         wiersz = wejscie.readLine();
       }
       wejscie.close();
       wyjscie.close();
     } catch (Exception e) {
       System.out.println(e.getMessage());
     }
   }
 }