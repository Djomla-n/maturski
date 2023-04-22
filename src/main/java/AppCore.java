import core.ApplicationFramework;
import core.Gui;
import gui.SwingGui;
import gui.view.MyFrame;

public class AppCore{
    public static void main(String[] args) {
        ApplicationFramework appCore= ApplicationFramework.getInstance();
        Gui gui= new SwingGui();
        appCore.izvrsi(gui);
        appCore.run();
    }
}
