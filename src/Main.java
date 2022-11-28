public class Main {

  public static void main(String[] args) {
    System.out.println("Hello Moth!");
    FlutterModel model = new FlutterModel();
    FlutterGUI gui = new FlutterGUIMoth(model);
    FlutterController controller = new FlutterController(model, gui);
    gui.setListener(controller);
    // replace with something else,?
    gui.repaint();
  }
}