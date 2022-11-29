
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
  public void addMoreColumns() {
    this.flutter.addColumn(false);
  }

  @Override
  public void moveColumns() {
    this.flutter.moveColumns();
  }

  @Override
  public void handleCollisions() {
    this.flutter.handleCollisions();
  }

  @Override
  public void startGame() {
    this.flutter.startGame();
  }

  @Override
  public void setListener(Listener listener) {
    this.listener = listener;
  }

  public void fall(int drop){
    this.flutter.dropMoth(drop);
  }

  public void setKeyListener(KeyListener kl) {
    this.kl = kl;
  }
}
