import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Lab8 {
  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      try {
        Lab8 window = new Lab8(2);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  public Lab8(Integer choice) {
    switch (choice) {
      case 1:
        Zadanie1 z1 = new Zadanie1();
        break;
      case 2:
        Zadanie2 z2 = new Zadanie2();
        break;
      // case 3:
      //   Zadanie3 z3 = new Zadanie3();
      //   break;
      default:
        break;
    }
  }
}

// Classes used:
// ImagePanel
// Slider
class Zadanie1 extends JFrame implements Runnable {
  Integer delay = 100;
  Thread thread = null;

  public Zadanie1() {
    setSize(800, 600);
    setTitle("ZAD1_IMIE_NAZWISKO");
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImagePanel imagePanel = new ImagePanel("./image.png", "./image2.png");
    Slider slider = new Slider();
    slider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        setDelay(slider.getValue());
      }
    });
    setDelay(slider.getValue());
    add(imagePanel, BorderLayout.CENTER); // borderlayout nie dziala bo klasa ImagePanel zawsze rysuje w 0,0
    add(slider, BorderLayout.SOUTH);
    this.thread = new Thread(this);
    thread.start();
  }

  // delay in milisecs
  private void setDelay(Integer val) {
    this.delay = 100 + val*10;
  }

  @Override
  public void run() {
    while (this.thread != null) {
      try {
        // Thread a nie thread bo sleep to satyczna metoda
        Thread.sleep(this.delay);
      } catch (InterruptedException e) {
        System.out.println(e.getMessage());
      }
      repaint();
    }
  }
}

class ImagePanel extends JPanel {
  private BufferedImage image;
  private BufferedImage image2;
  private Boolean showFirstImage = true;

  public ImagePanel(String path, String path2) {
    try {
      this.image = resizeImage(ImageIO.read(new File(path)), 200, 200);
      this.image2 = resizeImage(ImageIO.read(new File(path2)), 200, 200);
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
    g.drawImage(this.showFirstImage ? image : image2, 0, 0, this);
    this.showFirstImage = !this.showFirstImage;
  }
}

class Slider extends JSlider {
  public Slider() {
    setVisible(true);
    setOrientation(JSlider.HORIZONTAL);
    setMinimum(0);
    setMaximum(100);
    setValue(50);
  }
}

class Zadanie2 extends JFrame implements Runnable {
  Thread thread = null;
  Integer delay = 20;

  public Zadanie2() {
    setTitle("ZAD1_IMIE_NAZWISKO");
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(new Kulka());
    pack();
    this.thread = new Thread(this);
    thread.start();
  }

  @Override
  public void run() {
    while (this.thread != null) {
      try {
        // Thread a nie thread bo sleep to satyczna metoda
        Thread.sleep(this.delay);
      } catch (InterruptedException e) {
        System.out.println(e.getMessage());
      }
      repaint();
    }
  }
}

class Kulka extends JPanel {
  private Integer speed;
  private Double angleRad;
  private Integer posX;
  private Integer posY;

  public Kulka () {
    this.posX = 50;
    this.posY = 50;
    this.speed = 15;
    this.angleRad = (11d/6d)*Math.PI;
    setPreferredSize(new Dimension(800, 600));

    JPanel buttonPanel = new JPanel();
    JButton xCollisionButton = new JButton("X collision");
    JButton yCollisionButton = new JButton("Y collision");
    xCollisionButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {xCollision();}
    });
    yCollisionButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {yCollision();}
    });
    buttonPanel.add(xCollisionButton);
    buttonPanel.add(yCollisionButton);
    add(buttonPanel);
  }
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(new Color(255, 0, 0));
    g.fillOval(this.posX, this.posY, 20, 20);
    this.posX += (int) Math.round(Math.cos(angleRad)*speed);
    this.posY += (int) Math.round(Math.sin(angleRad)*speed);
    if (this.posY < 0) xCollision();
    if (this.posX > 800) yCollision();
    if (this.posY > 600) xCollision();
    if (this.posX < 0) yCollision();
  }

  private void xCollision() {this.angleRad = 2d*Math.PI - this.angleRad;}
  private void yCollision() {this.angleRad = Math.PI - this.angleRad;}
}
