package gui.controller;

import lombok.Getter;

import java.awt.event.ActionEvent;

@Getter
public class oboj extends AbstractMaturskiAction {
    public oboj(){
        putValue(NAME, "oboj");
    }
    public void actionPerformed(ActionEvent arg0){
        System.out.println("cao");
    }
}