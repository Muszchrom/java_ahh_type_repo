import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Lab7 {
  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      try {
        Lab7 app = new Lab7(3);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    });
  }

  public Lab7(Integer zadanie) {
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

class AppWrapper extends JFrame {
  public AppWrapper() {
    setSize(800, 600);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}

class Zadanie1 extends AppWrapper {
  PrintWriter writer;
  public Zadanie1() {
    setTitle("ZAD1_IMIE_NAZWISKO");
    Zadanie1Panel z = new Zadanie1Panel();

    JButton testButton = new JButton("Dodaj do pliku");
    testButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        saveToFile(z.getValues());
      }
    });

    add(z, BorderLayout.CENTER);
    add(testButton, BorderLayout.SOUTH);
    setupWriter();
  }

  private void saveToFile(String data) {
    if (this.writer != null) {
      this.writer.write(data + "\n");
      this.writer.flush();
    } else {
      setupWriter();
    }
  }

  private void setupWriter() {
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(this);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
      try {
        this.writer = new PrintWriter(new BufferedWriter(new FileWriter(chooser.getSelectedFile().getPath(), true)));
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
       System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
    }
  }
}

class Zadanie1Panel extends JPanel {
  protected JTextField name;
  protected JTextField s_name;
  protected JList<Integer> age;
  protected JRadioButton gender_m;
  protected JRadioButton gender_f;
  protected ButtonGroup gender;
  protected JComboBox<String> fieldOfStudy;
  protected JList<String> monthOfBirth;
  protected JCheckBox graduate;

  public Zadanie1Panel() {
    Integer[] a = IntStream.range(0, 121).boxed().toArray(Integer[]::new);
    String[] f = {"Budownictwo", "Architektura", "Elektrotechnika", "Informatyka", "Inżynieria biomedyczna (kierunek prowadzony razem z Wydziałem Mechanicznym)", "Inżynierskie zastosowania informatyki w elektrotechnice", "Inżynieria multimediów", "Mechatronika (kierunek prowadzony razem z Wydziałem Mechanicznym)", "Inżynieria recyklingu (NOWY KIERUNEK!)", "Inżynieria odnawialnych źródeł energii", "Inżynieria środowiska", "Energetyka (NOWY KIERUNEK!)", "Mechanika i budowa maszyn", "Mechatronika (kierunek prowadzony razem z Wydziałem Elektrotechniki i Informatyki)", "Zarządzanie i inżynieria produkcji ", "Transport", "Inżynieria biomedyczna (kierunek prowadzony wspólnie z Wydziałem Elektrotechniki i Informatyki)", "Robotyzacja procesów wytwórczych", "Inżynieria pojazdów (NOWY KIERUNEK!)", "Matematyka (studia inżynierskie)", "Edukacja techniczno-informatyczna", "Inżynieria bezpieczeństwa", "Inżynieria i analiza danych", "Zarządzanie", "Finanse i rachunkowość", "Marketing i komunikacja rynkowa", "Inżynieria logistyki", "Sztuczna inteligencja w biznesie"};
    for (int i=0; i<f.length; i++) {
      if (f[i].length() > 20) {
        f[i] = f[i].substring(0, 15) + "...";
      } 
    }
    String[] m = {"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"};


    this.name = new JTextField();
    this.s_name = new JTextField();
    this.age = new JList<>(a);
    this.gender_m = new JRadioButton();
    this.gender_m.setSelected(true);
    this.gender_f = new JRadioButton();
    this.gender = new ButtonGroup();
    this.fieldOfStudy = new JComboBox<>(f);
    this.monthOfBirth = new JList<>(m);
    this.graduate = new JCheckBox();
    
    gender.add(gender_f); gender.add(gender_m);
    addInputs();
  }

  public String getValues() {
    String s = this.name.getText() + "\t" + 
               this.s_name.getText() + "\t" +
               this.age.getSelectedValue() + "\t" +
               (this.gender_m.isSelected() ? "M" : "K") + "\t" +
               this.fieldOfStudy.getSelectedItem() + "\t" +
               this.monthOfBirth.getSelectedValue() + "\t" +
               this.graduate.isSelected() + "\t";
    return s;
  }

  private <T> JPanel smallWrapper(T input, String label) {
    JPanel wrapper = new JPanel();
    wrapper.setLayout(new BorderLayout());
    wrapper.add(new JLabel(label), BorderLayout.NORTH);
    if (input instanceof JTextField || 
        input instanceof JRadioButton || 
        input instanceof JCheckBox || 
        input instanceof JComboBox) {
      wrapper.add((Component) input, BorderLayout.SOUTH);
    } else if (input instanceof JList) {
      // Copy because im lazy
      JList inputCopy = (JList) input;
      inputCopy.setVisibleRowCount(5);
      inputCopy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      JScrollPane ageScrollPane = new JScrollPane(inputCopy);
      wrapper.add(ageScrollPane, BorderLayout.SOUTH);
    }
    return wrapper;
  }

  private void addInputs() {
    add(smallWrapper(this.name,         "Imię             "));
    add(smallWrapper(this.s_name,       "Nazwisko         "));
    add(smallWrapper(this.age          ,"Wiek             "));
    add(smallWrapper(this.gender_m,     "Mężczyzna        "));
    add(smallWrapper(this.gender_f,     "Kobieta          "));
    add(smallWrapper(this.fieldOfStudy, "Kierunek studiów "));
    add(smallWrapper(this.monthOfBirth, "Miesiąc urodzenia"));
    add(smallWrapper(this.graduate,     "Absolwent        "));
  }
}

class Zadanie2 extends AppWrapper{
  private JLabel appTitle;
  private ArrayList<Zadanie2PanelComponent> dataReferences;
  private JScrollPane scrollPane;

  public Zadanie2() {
    setTitle("ZAD2_IMIE_NAZWISKO");
    this.appTitle = new JLabel("Otwieranie pliku ...");
    this.dataReferences = new ArrayList<Zadanie2PanelComponent>();

    // Button that loads data
    JButton testButton = new JButton("Otwórz plik");
    testButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // On every data load new panel is created and the old one is deleted by updateScrollPane()
        setupReaderAndLoadData();
        JPanel rowsWrapper = new JPanel();
        rowsWrapper.setLayout(new GridLayout(0, 1));
        for (Zadanie2PanelComponent z2 : getReferences()) {
          rowsWrapper.add(z2);
        }
        JScrollPane scrollPane = new JScrollPane(rowsWrapper, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        updateScrollPane(scrollPane);
      }
    });

    // Button that saves data
    JButton saveButton = new JButton("Zapisz do pliku");
    saveButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        saveToFile();
      }
    });

    add(this.appTitle, BorderLayout.NORTH);

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.add(testButton);
    buttonsPanel.add(saveButton);
    add(buttonsPanel, BorderLayout.SOUTH);
  }

  // remove reference to old panel since without that the new panel would overlap 
  // save reference to new panel and add it to the frame 
  private void updateScrollPane(JScrollPane pane) {
    if (this.scrollPane != null) {
      remove(this.scrollPane);
    }
    this.scrollPane = pane;
    add(this.scrollPane, BorderLayout.CENTER);
    setVisible(true);
  }

  private ArrayList<Zadanie2PanelComponent> getReferences() {
    return this.dataReferences;
  }

  // Loads a file and returns array of references to Zadanie2PanelComponent which is basically Zadanie1Panel
  private void setupReaderAndLoadData() {
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
    chooser.setFileFilter(filter);
    if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      try {
        this.appTitle.setText(chooser.getSelectedFile().getName());
        BufferedReader reader = new BufferedReader(new FileReader(chooser.getSelectedFile()));
        String line;
        while ((line = reader.readLine()) != null) {
          Zadanie2PanelComponent z2 = new Zadanie2PanelComponent();
          z2.setValues(line);
          this.dataReferences.add(z2);
        }
        reader.close();
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private void saveToFile() {
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
    chooser.setFileFilter(filter);

    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      try {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(chooser.getSelectedFile().getPath(), true)));
        for (Zadanie2PanelComponent z2 : getReferences()) {
          writer.write(z2.getValues() + "\n");
          writer.flush();
        }
        writer.close();
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }

}

class Zadanie2PanelComponent extends Zadanie1Panel {
  public Zadanie2PanelComponent() {
    super();
  }

  public void setValues(String line) {
    line = line.substring(0, line.length() - 1);
    String[] strarr = line.split("\t");

    this.name.setText(strarr[0]);
    this.s_name.setText(strarr[1]);
    if (strarr[2].length() != 0) this.age.setSelectedValue(Integer.parseInt(strarr[2]), true);
    if (strarr[3].equals("M")) this.gender_m.setSelected(true);
    else this.gender_f.setSelected(true);
    if (strarr[4].length() != 0) this.fieldOfStudy.setSelectedItem(strarr[4]);
    if (strarr[5].length() != 0) this.monthOfBirth.setSelectedValue(strarr[5], true);
    if (strarr[6].equals("true")) this.graduate.setSelected(true);
    else this.graduate.setSelected(false);
  } 

}

class Zadanie3 extends JFrame {
  private JPanel btnPanel;
  private JButton[] buttons;
  private int[] fs;
  private int j = 0;
  private Boolean turn = true;
  private Boolean gameEnded = false;

  public Zadanie3() {
    setSize(600, 600);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("ZAD3_IMIE_NAZIWSKO");
    setupButtons();
  }

  private int getJ() {
    return this.j;
  } 

  private void setupButtons() {
    this.btnPanel = new JPanel();
    this.btnPanel.setLayout(new GridLayout(3, 3));
    this.buttons = new JButton[9];
    this.fs = new int[9];
    for (; this.j<9; this.j++) {
      int i = getJ();
      JButton button = new JButton();
      button.setFont(new Font("Arial", Font.PLAIN, 40));
      button.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {buttonAction(i);}});
      this.buttons[i] = button;
      this.btnPanel.add(button);
    }
    add(this.btnPanel);
  }

  private void buttonAction(int buttonId) {
    if (this.gameEnded) {restart(); return;} 
    if (this.fs[buttonId] != 0) return;

    this.buttons[buttonId].setText(this.turn ? "O" : "X");
    this.fs[buttonId] = this.turn ? 1 : -1;
    this.turn = !this.turn;
  
    loopCheck(0, 1);
    loopCheck(0, 3);
    loopCheck(0,4);
    loopCheck(1, 3);
    loopCheck(2, 2);
    loopCheck(2, 3);
    loopCheck(3, 1);
    loopCheck(6, 1);
  }

  private void loopCheck(int start, int diff) {
    int sum = 0;
    for (int i=start; i<3*diff; i+=diff) {
      sum += this.fs[i];
    }
    if (sum == 3) {
      this.gameEnded = true;
      for (int i=0; i<9; i++) this.buttons[i].setBackground(Color.BLACK);
      this.buttons[4].setFont(new Font("Arial", Font.BOLD, 16));
      this.buttons[4].setBackground(Color.WHITE);
      this.buttons[4].setText("Wygrywa kółko");
    } else if (sum == -3) {
      this.gameEnded = true;
      for (int i=0; i<9; i+=2) this.buttons[i].setBackground(Color.BLACK);
      
      this.buttons[4].setFont(new Font("Arial", Font.BOLD, 16));
      this.buttons[4].setText("Wygrywa krzyżyk");
      this.buttons[4].setForeground(Color.WHITE);;
    }
  }

  private void restart() {
    remove(this.btnPanel);
    this.j = 0;
    this.gameEnded = false;
    this.turn = true;

    setupButtons();
    revalidate();
    repaint();
  }
}