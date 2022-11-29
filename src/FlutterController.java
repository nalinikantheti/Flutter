import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FlutterController implements Listener, KeyListener {
  private FlutterModel model;
  private FlutterGUI gui;
  private int ticks, acceleration;

  private boolean started;


  public FlutterController(FlutterModel model, FlutterGUI gui) {
    this.started = false;
    this.model = model;
    this.gui = gui;
    this.ticks = 0;
    this.acceleration = 0;
  }

  @Override
  public void actionPerformed(String actionCommand) {

    if (started) {
      ticks++;
      if (ticks % 2 == 0 && acceleration < 15 && ticks > 5) {
        acceleration += 1;
      }
      gui.fall(acceleration);
      gui.moveColumns();
      gui.addMoreColumns();
      gui.handleCollisions();
      gui.repaint();
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    this.started = true;
    gui.startGame();
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
