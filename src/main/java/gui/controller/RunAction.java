package gui.controller;

import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
public class RunAction extends AbstractMaturskiAction{
    public RunAction(){
        putValue(NAME, "run");
    }
    public void actionPerformed(ActionEvent e){
            System.out.println("cao");
    }


}