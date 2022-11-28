public class FlutterController implements Listener {
  private FlutterModel model;
  private FlutterGUI gui;
  private int ticks, ymotion;


  public FlutterController(FlutterModel model, FlutterGUI gui) {
    this.model = model;
    this.gui = gui;
    this.ticks = 0;
    this.ymotion = 0;
  }

  @Override
  public void actionPerformed(String actionCommand) {

    System.out.println("actionperformed called");
    ticks++;
    if (ticks % 2 == 0 && ymotion < 15) {
      ymotion += 2;
    }
    gui.fall(ymotion);
    gui.repaint();
  }
}
