package app;

import gui.view.MyFrame;

public class Main {
    public static void main(String[] args) {
        //ApplicationFramework  main= ApplicationFramework.getInstance();
        //Gui gui= new SwingGui();//u gui dodaje novi SwingGui
        //main.izvrsi(gui);//poziva izvrsi iz app..Frameworka koji dodaje ovaj gui iz SwingGuia u Gui
        //main.run();//pokrece taj dodati gui
        AppCore appCore = new AppCore();
        MyFrame myFrame = MyFrame.getInstance();
        myFrame.setAppCore(appCore);
    }
}
