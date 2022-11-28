import java.awt.*;

public interface FlutterGUI {
  void repaint();
  void setListener(Listener listener);

  void fall(int drop);
}
