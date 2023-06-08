package gui.controller;

import app.AppCore;
import commands.CommandManager;
import commands.Commands;
import commands.implementation.RunCommand;
import core.ApplicationFramework;
import gui.view.MyFrame;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

@Getter
public class RunAction extends AbstractMaturskiAction{
    AppCore appCore = new AppCore();
    public void actionPerformed(ActionEvent e){
        String tekst = MyFrame.getInstance().getTekstPanel().getText();
        tekst.toString();
        JTextPane jTextPane = MyFrame.getInstance().getTekstPanel();
        //String [] niz = tekst.split(" \t\n");
        try {
            if(appCore.proveriUpit(tekst)) {
                Commands command = new RunCommand(jTextPane);
                ApplicationFramework.getInstance().getCommandManager().addCommand(command);
                appCore.posaljiUpit(tekst);
                MyFrame.getInstance().getTekstPanel().setText("");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }


}