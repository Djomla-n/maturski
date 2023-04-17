package GUI;

import JsonFajlovi.proveraTeksta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dugme2 extends JButton implements ActionListener {

    public dugme2(String tekst) {
        super(tekst);
        addActionListener(this::actionPerformed);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this) {
            proveraTeksta pt=new proveraTeksta();
            pt.proveriUnetiTekst(MyFrame.getTextPane());
        }
    }
}