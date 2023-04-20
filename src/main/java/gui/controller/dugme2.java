package gui.controller;

import gui.MyFrame;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@Getter
public class dugme2 extends JButton implements ActionListener {
    private String[] niz;
    private String[] funkcije=new String[4];
    public dugme2(String tekst) {
        super(tekst);
        addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this) {
            MyFrame mf = MyFrame.getInstance();


            //niz1=mf.tekstIzTekstPanela().split(" ");
          //  System.out.println(niz1[1]);
        }
    }
    public boolean uporedi(String s1){
            return true;
    }

}