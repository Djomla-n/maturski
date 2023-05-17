package gui.controller;

import core.ApplicationFramework;

import java.awt.event.ActionEvent;

public class UndoAction extends AbstractMaturskiAction{
    @Override
    public void actionPerformed(ActionEvent e) {
        ApplicationFramework.getInstance().getCommandManager().undoCommand();
    }
}
