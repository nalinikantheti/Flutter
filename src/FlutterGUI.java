import java.awt.event.KeyListener;

public interface FlutterGUI {
  void setListener(Listener listener);
  void setKeyListener(KeyListener kl);
  void increaseTick(int drop);
  void startGame();
  boolean isGameOver();
  void restartGame();
  void flap();

  void unflap();
}
