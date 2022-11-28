import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class FlutterPanel extends JPanel {
  private Rectangle moth;
  private int width;
  private int height;
  private ArrayList<Rectangle> columns;

  private Random rand;

  public FlutterPanel(int width, int height) {
    this.width = width;
    this.height = height;
    moth = new Rectangle(width / 2 - 10, height / 2 - 10, 20, 20);
    this.columns = new ArrayList<>();
    rand = new Random();
    this.addColumn(true);
    this.addColumn(true);
    this.addColumn(true);
    this.addColumn(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawSky(g);
    drawMoth(g);
    drawGround(g);
  }

  private void drawGround(Graphics g) {
    g.setColor(Color.ORANGE);
    g.fillRect(0, height - 120, width, 150);
    g.setColor(Color.green);
    g.fillRect(0, height - 120, width, 20);
  }

  private void drawMoth(Graphics g) {
    g.setColor(Color.red);
    g.fillRect(moth.x, moth.y, moth.width, moth.height);
  }

  private void drawSky(Graphics g) {
    g.setColor(new Color(83, 154, 187));
    g.fillRect(0, 0, width, height);
  }

  private void drawColumn(Graphics g, Rectangle column) {
    g.setColor(Color.green.darker());
    g.fillRect(column.x, column.y, column.width, column.height);
  }

  private void addColumn(boolean start) {
    int space = 300;
    int width = 100;
    int height = 50 + rand.nextInt(300);
    if (start) {
      columns.add(new Rectangle(this.width + width + columns.size() * 300,
              this.height - height - 120, width, height));
      columns.add(new Rectangle(this.width + width + (columns.size() - 1) * 300,
              0, width, this.height - height - space));
    } else {
      columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600,
              this.height - height - 120,width, height));
      // should it be columnds - 2??
      columns.add(new Rectangle(columns.get(columns.size() - 1).x,
              0, width, this.height - height - space));
    }
  }

  public void dropMoth(int drop){
    moth.y += drop;
  }

}
