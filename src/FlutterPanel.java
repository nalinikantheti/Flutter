import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class FlutterPanel extends JPanel {
  private Rectangle moth;
  private int width;
  private int height;
  private int speed;
  private int score;
  private ArrayList<Rectangle> columns;
  private boolean started;
  public boolean gameOver;
  private Random rand;


  public FlutterPanel(int width, int height) {
    this.gameOver = false;
    this.started = false;
    this.width = width;
    this.height = height;
    this.speed = 3;
    this.score = 0;
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
    if (!gameOver && started) {
      g.setColor(Color.white);
      g.drawString(String.valueOf(score), width / 2 - 25, 100);
    }

  }

  private void showStartScreen(Graphics g) {
    if (!started) {
      g.setFont(new Font("Arial", 1, 60));
      g.setColor(Color.white);
      g.drawString("Press Any Key to Start!", 30, height / 2);
    }
  }

  private void drawGround(Graphics g) {
    g.setColor(Color.ORANGE);
    g.fillRect(0, height - 120, width, 150);
    g.setColor(Color.green);
    g.fillRect(0, height - 120, width, 20);
  }

  private void drawMoth(Graphics g) {
    g.setColor(Color.lightGray);
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
    int space = 500;
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
    if (moth.y < 0) {
      gameOver = true;
    }
    if (moth.y >= height - 120 - moth.height) {
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

  public void startGame() {
    this.started = true;
  }


  public void restartGame() {
    moth = new Rectangle(width / 2 - 10, height / 2 - 10, 20, 20);
    this.columns.clear();
    this.score = 0;
    this.addColumn(true);
    this.addColumn(true);
    this.addColumn(true);
    this.addColumn(true);
    gameOver = false;
    started = false;
  }

  public void flap() {
    this.moth.width = (int) (moth.width * 1.5);
  }

  public void unflap() {
    this.moth.width = (int) (moth.width / 1.5);
  }

  public void updateScore() {
    for (int i = 0 ; i < columns.size(); i += 1) {
      Rectangle col = columns.get(i);
      if (moth.x + moth.width / 2 == col.x + col.width / 2 - 9
              && moth.x + moth.width / 2 < col.x + col.width / 2 + 9) {
        System.out.println(score);
        System.out.println("BREAK ");
        score++;
      }
    }
  }
}
