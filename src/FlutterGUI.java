import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public interface FlutterGUI {
  void setListener(Listener listener);
  void setKeyListener(KeyListener kl);
  void fall(int drop);
  void repaint();
  void addMoreColumns();
  void moveColumns();
  void handleCollisions();
  void startGame();

}
