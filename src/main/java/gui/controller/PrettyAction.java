package gui.controller;

import gui.view.MyFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PrettyAction extends AbstractMaturskiAction{
    private static String[] nizReciVelika;
    private static String[] nizReciNorm;
    private static String[] nizFunkcija={"SELECT", "FROM", "WHERE", "UPDATE", "INSERT INTO"};
    private static String tekstPanel;
    private String tekstPane;
    private MyFrame instance;

    @Override
    public void actionPerformed(ActionEvent e) {
        tekstPanel= MyFrame.getTekstPanel().getText();
        instance=MyFrame.getInstance();
        nizReciNorm= tekstPanel.split(" ");
        nizReciVelika=tekstPanel.toUpperCase().split(" ");
        tekstPane="";

        for (int i = 0; i < nizReciVelika.length; i++) {
            for (int j = 0; j < nizFunkcija.length; j++) {
                if(nizReciVelika[i].equals(nizFunkcija[j])){
                    if(i==0) {
                        nizReciNorm[i] =  nizReciVelika[i];
                    }
                    else {
                        nizReciNorm[i] = "\n" + nizReciVelika[i];
                    }
                }
            }
        }
        for (int i = 0; i < nizReciNorm.length; i++) {
            tekstPane=tekstPane + nizReciNorm[i]+" ";
        }
        MyFrame.updateTextPane(tekstPane);
    }

}
