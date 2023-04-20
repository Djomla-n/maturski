package gui;

import core.Gui;
import gui.view.MyFrame;

public class SwingGui implements Gui {
    private MyFrame instance;

    public SwingGui(){
    }
    public void start(){
        instance=MyFrame.getInstance();
    }
}
