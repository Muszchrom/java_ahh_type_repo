import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Lab8 {
  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      try {
        Lab8 window = new Lab8(1);
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
      // case 2:
      //   Zadanie2 z2 = new Zadanie2();
      //   break;
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