
import java.awt.event.ActionListener;

import javax.swing.*;

public class FlutterGUIMoth extends JFrame implements FlutterGUI{
  private FlutterModel model;
  private final int width;
  private final int height;
  private Timer timer;
  private ActionListener redirect;
  private Listener listener;
  private FlutterPanel flutter;


  public FlutterGUIMoth(FlutterModel model) {
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
    this.setTitle("~*: FLUTTER :*~");
    this.setSize(width, height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.add(flutter);
    this.setVisible(true);
    timer.start();
  }

  @Override
  public void repaint() {
    this.flutter.repaint();
  }

  @Override
  public void setListener(Listener listener) {
    this.listener = listener;
  }

  public void fall(int drop){
    this.flutter.dropMoth(drop);
  }
}
