/** Co to jest token?
 * na przykładzie pythona 
 * token = string.split(" ")
 * zależnie od ustawień tokeny można inaczej rozdzielać
 */

 /** Do czego służy konstrukcja try - catch? Podaj przykład dla jakiejś operacji matematycznej
 * Służy ona do obsługi wyjątków/errorów
 * spróbuj {
 *   wykonać kod
 * } złap(wyjątek) {
 *   i obsłuż go
 * }
 */

 /** Wymień przynajmniej pięć metod z klasy Math wraz z zastosowaniem
 *  min, max, abs, sqrt, pow
 */

/** Z czego mogą wynikać błędy w parswoaniu tokenów?
 * znak "/" nie jest interpretowany jako TT_WORD ani TT_NUMBER ani w sumie nie wiadomo co
 * znaki + - * % po użyciu stringTokenizer.ttype wyświetlane są jako ich odpowiedniki ASCII czyli np 43
 * znaki albo ciągi znaków takich jak "a", "aalfabeet" są wyświetlane jako -3 czyli StringTokenizer.TT_WORD
 * liczby są wyświetlane jako -2 czyli StringTokenizer.TT_NUMBER
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
  public static void main(String[] args) {
    // zadanie1();
    zadanie2i3();
  }

   /** Program dzielący dwie liczby typu float odczytywane poprzez dwukrotne wywołanie 
  *   BufferedReader z obsługa następujących wyjątków:
  * dzielenie przez zero
  * podawanie niewłaściwego argumentu
  * podawanie pustego argumentu
  */

  private static void zadanie1() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    float a, b;
    try {
      System.out.print("Podaj a: ");
      a = Float.parseFloat(reader.readLine());
      System.out.print("Podaj b: ");
      b = Float.parseFloat(reader.readLine());

      if (b == 0) throw new ArithmeticException("Nie potrafię dzielić przez 0");
      System.out.println(a/b);

    } catch (IOException | NumberFormatException | NullPointerException e) {
      System.out.println("BŁĄD: Podano nieprawidłowe dane wejściowe");

    } catch (ArithmeticException e) {
      System.out.println("BŁĄD:" + e.getMessage());
    }
  }

  /** Kalkulator odczytujący operacje matematyczne przy wykorzystaniu jednego odczytu i tokenów. 
   * Zadanie podawane w formacie LICZBA DZIAŁANIE LICZBA. Obsługa dodawania, odejmowania, mnożenia, 
   * dzielenia, procentów (np. 50 % 16 daje wynik 8). Obsługa wyjątków z poprzedniego zadania i odporność na niepoprawne argumenty.
  */

  /** Rozbudowa kalkulatora o operacje tylko z dwoma tokenami. Zaimplementowanie wartości bezwzględnej, 
   * zaokrąglenia, pierwiastkowania, sinusa, zwracania liczby w zakresie od 0 do podanej liczby. 
   * Format DZIAŁANIE LICZBA. Przykładowo (sin 30 zwraca 0.5, a random 10 zwraca liczbę od 0 do 10)
  */
  private static void zadanie2i3() {
    // tokenizer setup
    StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    streamTokenizer.eolIsSignificant(true);
    streamTokenizer.ordinaryChar('/');

    // stupid ass try catch
    try {
      streamTokenizer.nextToken();
      // Kalkulator z zadania 2
      if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
        Double a = streamTokenizer.nval;
        streamTokenizer.nextToken();

        if (
          streamTokenizer.ttype != 37 && 
          streamTokenizer.ttype != 42 &&
          streamTokenizer.ttype != 43 &&
          streamTokenizer.ttype != 45 &&
          streamTokenizer.ttype != 47
        ) throw new ArithmeticException("Podany znak nie jest obsługiwany");
        char c = (char) streamTokenizer.ttype;
        streamTokenizer.nextToken();
        if (streamTokenizer.ttype != StreamTokenizer.TT_NUMBER) throw new ArithmeticException("Proszę podać liczbę");
        Double b = streamTokenizer.nval;
        switch (c) {
          case '+':
            System.out.println(a + b);
            break;
          case '-':
            System.out.println(a - b);
            break;
          case '*':
            System.out.println(a * b);
            break;
          case '/':
            if (b == 0) throw new ArithmeticException("Nie potrafię dzielić przez 0");
            System.out.println(a / b);
            break;
          case '%':
            System.out.println(a/100*b);
            break;
          default:
            break;
        }
      // Kalkulator z zadania 3
      } else if (streamTokenizer.ttype == StreamTokenizer.TT_WORD) {
        String option = streamTokenizer.sval;
        streamTokenizer.nextToken();
        if (streamTokenizer.ttype != StreamTokenizer.TT_NUMBER) {
          throw new ArithmeticException("Proszę podać liczbę");
        }
        Double a = streamTokenizer.nval;
        switch (option) {
          case "abs":
            System.out.println(Math.abs(a));
            break;
          case "round":
            System.out.println(Math.round(a));
            break;
          case "sqrt":
            if (a < 0) throw new ArithmeticException("Nie potrafię oblicz pierwiastka z a < 0");
            System.out.println(Math.sqrt(a));
            break;
          case "sin":
            System.out.println(Math.sin(a));
            break;
          case "random":
            if (a < 0) throw new ArithmeticException("Liczba musi byc większa od 0");
            System.out.println(Math.random()*Math.abs(a));
            break;
          default:
            break;
        }
    
      } else throw new Exception("Invalid token");
    } catch (IOException e) {
      System.out.println("Błąd który zawsze muszę obsłużyć 🫠");
    } catch (ArithmeticException e) {
      System.out.println("Błąd: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("Błąd: " + e.getMessage());
    }

  }
}