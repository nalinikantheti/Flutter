public class Main {

  public static void main(String[] args) {
    System.out.println("Hello Moth!");
    FlutterModel model = new FlutterModel();
    FlutterGUI gui = new FlutterFrame(model);
    FlutterController controller = new FlutterController(model, gui);
    gui.setListener(controller);
    gui.setKeyListener(controller);
  }
}