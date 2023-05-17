package gui.controller;

import core.ApplicationFramework;

import java.awt.event.ActionEvent;

public class RedoAction extends AbstractMaturskiAction{
    @Override
    public void actionPerformed(ActionEvent e) {
        ApplicationFramework.getInstance().getCommandManager().doCommand();
    }
}
