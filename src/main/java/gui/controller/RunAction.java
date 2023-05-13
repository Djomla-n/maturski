package gui.controller;

import app.AppCore;
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
        try {
            appCore.posaljiUpit(tekst);
           // MyFrame.getInstance().getTekstPanel().setText("");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }


}