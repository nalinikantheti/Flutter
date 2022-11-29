import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class FlutterPanel extends JPanel {
  private Rectangle moth;
  private int width;
  private int height;
  private int speed;
  private ArrayList<Rectangle> columns;
  private boolean gameOver, started;
  private Random rand;


  public FlutterPanel(int width, int height) {
    this.gameOver = false;
    this.started = false;
    this.width = width;
    this.height = height;
    this.speed = 3;
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
    drawGround(g);
    for (Rectangle column : this.columns) {
      this.drawColumn(g, column);
    }
    drawMoth(g);
    showStartScreen(g);
    showEndScreen(g);

  }

  private void showStartScreen(Graphics g) {
    if (!started) {
      g.setFont(new Font("Arial", 1, 80));
      g.setColor(Color.white);
      g.drawString("Press Any Key to Start!", 25, height / 2);
    }
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

  public void addColumn(boolean start) {
    int space = 300;
    int width = 100;
    int height = 50 + rand.nextInt(300);
    if (start) {
      columns.add(new Rectangle(this.width + width + columns.size() * 200,
              this.height - height - 120, width, height));
      columns.add(new Rectangle(this.width + width + (columns.size() - 1) * 200,
              0, width, this.height - height - space));
    } else {
      columns.add(new Rectangle(columns.get(columns.size() - 1).x + 400,
              this.height - height - 120, width, height));
      columns.add(new Rectangle(columns.get(columns.size() - 1).x,
              0, width, this.height - height - space));
    }
  }

  public void dropMoth(int drop) {
    moth.y += drop;
  }

  public void moveColumns() {
    for (int i = 0; i < this.columns.size(); i++) {
      Rectangle col = this.columns.get(i);
      col.x -= speed;

      if (col.x + col.width < 0) {
        this.columns.remove(col);
      }
    }
  }

  public void handleCollisions() {
    for (Rectangle col : this.columns) {
      if (moth.intersects(col)) {
        gameOver = true;
        moth.x = col.x - moth.width;
      }
    }
    if (moth.y < 0 || moth.y > height - 120 - moth.height) {
      gameOver = true;
      moth.y = this.height - 120 - moth.height;
    }
  }

  public void showEndScreen(Graphics g) {
    if (gameOver) {
      this.started = true;
      g.setFont(new Font("Arial", 1, 100));
      g.setColor(Color.white);
      g.drawString("GAME OVER", 75, height / 2);
    }
  }

  public void startGame(){
    this.started = true;
  }

}
