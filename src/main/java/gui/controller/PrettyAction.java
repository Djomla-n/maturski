package gui.controller;

import gui.view.MyFrame;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PrettyAction extends AbstractMaturskiAction{
    private String[] nizReciVelika;
    private String[] nizReciNorm;
    private String[] nizFunkcija={"SELECT", "FROM", "WHERE", "UPDATE", "AS", "OR", "IN"};


    @Override
    public void actionPerformed(ActionEvent e) {
        JTextPane pocetan = MyFrame.getInstance().getTekstPanel();
        nizReciNorm = pocetan.getText().split(" ");
        nizReciVelika=pocetan.getText().toUpperCase().split(" ");
        pocetan.setText("");

        for (int i = 0; i < nizReciVelika.length; i++) {
            for (int j = 0; j < nizFunkcija.length; j++) {
                if (nizReciVelika[i].equals(nizFunkcija[j])) {
                    if (nizReciVelika[i].equals("AS") || nizReciVelika[i].equals("OR") || nizReciVelika[i].equals("IN")) {
                            nizReciNorm[i]= nizReciVelika[i];
                    }
                    else {
                        if (i == 0) {
                            nizReciNorm[i]= nizReciVelika[i];
                        } else {
                            nizReciNorm[i]= "\n" + nizReciVelika[i];
                        }
                    }
                }
            }
        }
        for (int i = 0; i < nizReciNorm.length; i++) {
            if(nizReciNorm[i].equals(nizReciNorm[i].toUpperCase())) {
                appendToPane(pocetan, nizReciNorm[i] + " ", Color.blue);
            }
            else if(nizReciNorm[i].toUpperCase().equals("INSERT") && nizReciNorm[i+1].toUpperCase().equals("INTO")){
                appendToPane(pocetan, "\n"+ nizReciNorm[i].toUpperCase() + " "+ nizReciNorm[i+1].toUpperCase() + " ", Color.blue);
            }
            else{
                appendToPane(pocetan, nizReciNorm[i] + " ", Color.black);
            }
        }
    }


    public static void appendToPane(JTextPane tp, String str, Color c) {
        SimpleAttributeSet boja = new SimpleAttributeSet();//pravi novi SimpleAttributeSet koji u sebi moze da sadrzi font, boju...
        StyleConstants.setForeground(boja, c);//u SimpleAttributeSet dodaje boju c
        int duzina = tp.getDocument().getLength();
        tp.setCaretPosition(duzina); // postavlja kursor na kraj kako bi tekst koji sledeci dodaje bio na kraju
        tp.setCharacterAttributes(boja, false); // u tekst panel posavlja boju
        tp.replaceSelection(str); // dodaje samo obojeni tekst
    }

}
