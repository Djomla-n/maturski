package commands.implementation;

import commands.Commands;
import gui.view.MyFrame;

import javax.swing.*;

public class RunCommand extends Commands {
    JTextPane jTextPane = new JTextPane();

    public RunCommand(JTextPane jTextPane) {
        this.jTextPane.setText(jTextPane.getText());
    }

    @Override
    public void doCommand() {
        MyFrame.getInstance().getTekstPanel().setText(jTextPane.getText());
    }

    @Override
    public void undoCommand() {
        MyFrame.getInstance().getTekstPanel().setText(jTextPane.getText());
    }
}
