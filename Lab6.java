import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// !!!!!!!!!!!!!!!!!
// CHANGE IMIE_NAZWISKO

public class Lab6 {
  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      try {
        Lab6 window = new Lab6(3);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  public Lab6(Integer choice) {
    switch (choice) {
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

class Zadanie1 extends JFrame {
  public Zadanie1() {
    setSize(800, 600);
    setTitle("ZAD1_IMIE_NAZWISKO");
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(new ImagePanel("./image.png"));

    JButton button = new JButton("Repaint");
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        repaint();
      }
    });
    add(button, BorderLayout.SOUTH);
  }
}

class Zadanie2 extends JFrame {
  public Zadanie2() {
    setTitle("ZAD2_IMIE_NAZWISKO");
    setSize(800, 600);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JScrollPane scrollPane = new JScrollPane(new TextPanel("./file.txt"));
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    add(scrollPane);
  }
}

class Zadanie3 extends JFrame {
  private Integer r = 100;
  private Integer g = 100;
  private Integer b = 100;

  JLabel labelr = new JLabel("R: " + this.r);
  JLabel labelg = new JLabel("R: " + this.r);
  JLabel labelb = new JLabel("R: " + this.r);

  GraphPanel graphPanel = new GraphPanel(new Color(100, 100, 100));

  public Zadanie3() {
    setTitle("ZAD2_IMIE_NAZWISKO");
    setSize(800, 600);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
    JPanel panelText = new JPanel();
    JPanel panel = new JPanel();
    panelText.setLayout(new GridLayout(3, 1));
    panel.setLayout(new GridLayout(3, 1));
    panel.setBackground(new Color(2, 255, 255));
    JScrollBar rscroll = new JScrollBar(JScrollBar.HORIZONTAL, 100, 1, 0, 254);
    JScrollBar gscroll = new JScrollBar(JScrollBar.HORIZONTAL, 100, 1, 0, 254);
    JScrollBar bscroll = new JScrollBar(JScrollBar.HORIZONTAL, 100, 1, 0, 254);
    rscroll.setPreferredSize(new Dimension(200, 50));

    rscroll.addAdjustmentListener(e -> {
      updateColor("R", rscroll.getValue());
    });
    gscroll.addAdjustmentListener(e -> {
      updateColor("G", rscroll.getValue());
    });
    bscroll.addAdjustmentListener(e -> {
      updateColor("B", rscroll.getValue());
    });


    panelText.add(labelr);
    panel.add(rscroll);
    panelText.add(labelg);
    panel.add(gscroll);
    panelText.add(labelb);
    panel.add(bscroll);

    JPanel combo = new JPanel();
    combo.add(panelText, BorderLayout.NORTH);
    combo.add(panel, BorderLayout.NORTH);
    add(combo, BorderLayout.WEST);
    add(graphPanel, BorderLayout.CENTER);
  }

  private void updateColor(String option, Integer value) {
    switch (option) {
      case "R":
        this.r = value;        
        this.labelr.setText("R: " + value);
        break;
      case "G":
        this.g = value;
        this.labelg.setText("G: " + value);
        break;
      case "B":
        this.b = value;  
        this.labelb.setText("B: " + value);
        break;
    }
    this.graphPanel.setColor(new Color(this.r, this.g, this.b));
    this.graphPanel.repaint();
  }
        
}

// zadanie1
class ImagePanel extends JPanel {
  private BufferedImage image;

  public ImagePanel(String path) {
    try {
      image = resizeImage(ImageIO.read(new File(path)), 200, 200);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
  // Overkill
  private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = resizedImage.createGraphics();
    g2.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
    g2.dispose();
    return resizedImage;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(image, 0, 0, this);
  }
}

// zadanie2
class TextPanel extends JTextArea {
  public TextPanel(String path) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(path));
      String line;
      while ((line = reader.readLine()) != null) {
        append(line + "\n");
      }
      reader.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    setFont(new Font("Arial", Font.PLAIN, 12));
    setForeground(Color.BLACK);
    setEditable(false);
  }
}

// zadanie3
class GraphPanel extends JPanel {
  private Color color;
  public GraphPanel(Color color) {
    this.color = color;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawGraph(g);
  }

  public void setColor(Color color) {
    this.color = color;
  }

  private void drawGraph(Graphics g) {
    try {
        BufferedReader reader = new BufferedReader(new FileReader("nums.txt"));
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        // g.clearRect(0, 0, getWidth(), getHeight());
        int x = 50;
        int gain = 10;
        String line;
        Integer prevVal = 0;
        while ((line = reader.readLine()) != null) {
          Integer val = Integer.parseInt(line);
          g.fillOval(x-2, getHeight()-val-2, 4, 4);
          if (x == 50) {
            prevVal = val;
            x += gain;
            continue;
          }
          g.drawLine(x-gain, getHeight() - prevVal, x, getHeight() - val);

          x += gain;
          prevVal = val;
        }
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}