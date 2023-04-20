package gui.view;

import gui.controller.ActionManager;

import javax.swing.*;

public class dugmici extends JButton {
    private JButton run;
    public dugmici(){
        run.addActionListener(ActionManager.pokreniAkciju());

    }
}
