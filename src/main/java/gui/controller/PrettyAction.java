package gui.controller;

import gui.view.MyFrame;
import resource.enums.Upiti;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class PrettyAction extends AbstractMaturskiAction{
    private String[] nizReciVelika;
    private String[] nizReciNorm;
    //private String[] nizFunkcija={"SELECT", "FROM", "WHERE", "UPDATE", "AS", "OR", "IN"};


    @Override
    public void actionPerformed(ActionEvent e) {
        Upiti[] funkcija = Upiti.values();
        String[] nizFunkcija = new String[funkcija.length];

        for (int i = 0; i < funkcija.length; i++) {
            nizFunkcija[i] = funkcija[i].toString();
        }

        JTextPane pocetan = MyFrame.getInstance().getTekstPanel();
        nizReciNorm = pocetan.getText().replace("\n", " ").split(" ");
        nizReciVelika=pocetan.getText().replace("\n", " ").toUpperCase().split(" ");
        pocetan.setText("");


        while(nizReciNorm[0].equals("")){
            nizReciNorm=pomeri(nizReciNorm, 0);
            nizReciVelika=pomeri(nizReciVelika, 0);
        }


        for (int i = 0; i < nizReciVelika.length; i++) {
            for (int j = 0; j < nizFunkcija.length; j++) {
                if (nizReciVelika[i].equals(nizFunkcija[j]) ) {
                    if (proveri(nizReciVelika[i])) {
                        nizReciNorm[i] = nizReciVelika[i];
                    }
                    else {
                        if (i == 0 ) {
                                nizReciNorm[i] = nizReciVelika[i];
                        }
                        else {
                            nizReciNorm[i]= "\n" + nizReciVelika[i];
                        }
                    }
                }
                else if(nizReciVelika.equals("")){
                    nizReciNorm=pomeri(nizReciVelika, i);
                }

            }
        }
        for (int i = 0; i < nizReciNorm.length; i++) {
            if(nizReciVelika[i].equals("INNER") || nizReciVelika[i].equals("RIGHT") || nizReciVelika[i].equals("LEFT") || nizReciVelika[i].equals("OUTHER") && nizReciVelika[i+1].equals("JOIN")){
                appendToPane(pocetan, nizReciNorm[i].toUpperCase() + " "+ nizReciNorm[i+1].toUpperCase() + " ", Color.blue);
                vrati(pocetan);
                i++;
            }
            else if(nizReciVelika[i].equals("INSERT") && nizReciVelika[i+1].equals("INTO")){
                appendToPane(pocetan, nizReciNorm[i].toUpperCase() + " "+ nizReciNorm[i+1].toUpperCase() + " ", Color.blue);
                vrati(pocetan);
                i++;
            }
            else if(nizReciVelika[i].equals("GROUP") || nizReciVelika[i].equals("ORDER") && nizReciVelika[i+1].equals("BY")){
                if(nizReciVelika[i].equals("GROUP")){
                    appendToPane(pocetan, "\n" + nizReciNorm[i].toUpperCase() + " "+ nizReciNorm[i+1].toUpperCase() + " ", Color.blue);
                }
                else{
                    appendToPane(pocetan, nizReciNorm[i].toUpperCase() + " "+ nizReciNorm[i+1].toUpperCase() + " ", Color.blue);
                }
                vrati(pocetan);
                i++;
            }
            else if(nizReciVelika[i].equals("CREATE") || nizReciVelika[i].equals("DROP") || nizReciVelika[i].equals("ALTER") && nizReciVelika[i+1].equals("TABLE")){
                if(i==0){
                    appendToPane(pocetan, nizReciNorm[i].toUpperCase() + " "+ nizReciNorm[i+1].toUpperCase() + " ", Color.blue);
                }
                else {
                    appendToPane(pocetan, "\n" + nizReciNorm[i].toUpperCase() + " "+ nizReciNorm[i+1].toUpperCase() + " ", Color.blue);
                }
                vrati(pocetan);
                i++;
            }
            else if(nizReciNorm[i].equals(nizReciNorm[i].toUpperCase())) {
                appendToPane(pocetan, nizReciNorm[i] + " ", Color.blue);
                vrati(pocetan);
            }
            else{
                appendToPane(pocetan, nizReciNorm[i] + " ", Color.black);
            }
        }
    }
    public boolean proveri(String naredba){
        String[] niz={"IN","AS","OR", "BETWEEN","NOT", "EXIST", "LIKE", "SET", "DISTINCT", "ASC", "DESC", "ON",
                "JOIN"};
        for (int i = 0; i < niz.length; i++) {
            if(naredba.equals(niz[i])) {
                return true;
            }
        }
        return false;
    }

    public String[] pomeri(String[] niz, int x){
        for (int i = x; i < niz.length-1; i++) {
            niz[i]= niz[i+1];
        }
        return  Arrays.copyOfRange(niz, 0, niz.length - 1);//uklanja poslednji element niza
    }


    public static void appendToPane(JTextPane tp, String str, Color c) {
        SimpleAttributeSet boja = new SimpleAttributeSet();//pravi novi SimpleAttributeSet koji u sebi moze da sadrzi font, boju...
        StyleConstants.setForeground(boja, c);//u SimpleAttributeSet dodaje boju c
        int duzina = tp.getDocument().getLength();
        tp.setCaretPosition(duzina); // postavlja kursor na kraj kako bi tekst koji sledeci dodaje bio na kraju
        tp.setCharacterAttributes(boja, false); // u tekst panel posavlja boju
        tp.replaceSelection(str); // dodaje samo obojeni tekst
    }
    public void vrati(JTextPane tp){
        SimpleAttributeSet vrati = new SimpleAttributeSet();
        StyleConstants.setForeground(vrati, Color.black);
        tp.setCharacterAttributes(vrati, false);
        //vraca boju u crnu
    }

}
