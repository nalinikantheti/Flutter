
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

public class FlutterFrame extends JFrame implements FlutterGUI{
  private FlutterModel model;
  private final int width;
  private final int height;
  private Timer timer;
  private ActionListener redirect;
  private Listener listener;
  private KeyListener kl;
  private FlutterPanel flutter;


  public FlutterFrame(FlutterModel model) {
    this.model = model;
    width = 800;
    height = 800;
    redirect = e -> {
      if (listener != null) {
        listener.actionPerformed(e.getActionCommand());
      }
    };

    timer = new Timer(2,  redirect);
    flutter = new FlutterPanel(width, height);
    this.setTitle("•~*:§ FLUTTER §:*~•");
    this.setSize(width, height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.add(flutter);
    this.setVisible(true);
    timer.start();
  }
  public void repaint(){
    this.flutter.repaint();
  }

  @Override
  public void startGame() {
    this.flutter.startGame();
  }

  @Override
  public boolean isGameOver() {
    return this.flutter.gameOver;
  }

  @Override
  public void restartGame() {
    this.flutter.restartGame();
  }

  @Override
  public void flap() {
    this.flutter.flap();
  }

  @Override
  public void unflap() {
    this.flutter.unflap();
  }

  @Override
  public void setListener(Listener listener) {
    this.listener = listener;
  }


  public void setKeyListener(KeyListener kl) {
    this.kl = kl;
    this.addKeyListener(kl);
  }

  @Override
  public void increaseTick(int drop) {
    flutter.dropMoth(drop);
    flutter.moveColumns();
    flutter.addColumn(false);
    flutter.handleCollisions();
    flutter.repaint();
    flutter.updateScore();
  }
}
