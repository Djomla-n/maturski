package gui.controller;

import gui.view.MyFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PrettyAction extends AbstractMaturskiAction{
    private static String[] nizReciVelika;
    private static String[] nizReciNorm;
    private static String[] nizFunkcija={"SELECT", "FROM", "WHERE", "UPDATE", "INSERT INTO"};
    private static String tekstPanel;
    private JTextPane tekstPane;
    @Override
    public void actionPerformed(ActionEvent e) {
        tekstPane=new JTextPane();
        tekstPanel= MyFrame.getTekstPanel().getText();
        nizReciNorm= tekstPanel.split(" ");
        nizReciVelika=tekstPanel.toUpperCase().split(" ");

        for (int i = 0; i < nizReciVelika.length; i++) {
            for (int j = 0; j < nizFunkcija.length; j++) {
                if(nizReciVelika[i].equals(nizFunkcija[j])){
                    tekstPane.setText(nizReciVelika[i]);
                    break;
                }
                else {
                    tekstPane.setText(nizReciNorm[i]);
                    break;
                }
            }
        }

        MyFrame.updateTextPane(tekstPane.getText());
    }

}
