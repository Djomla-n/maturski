package jsnofajlovi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class proveraTeksta {
    private List<String> reci;

    public proveraTeksta() {
        ucitajReciIzJsonFajla("reci.json");
    }

    private void ucitajReciIzJsonFajla(String s) {
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(new File(s));
            //objectMapper- koristi se da se prevode(mapiraju) fajlovi iz JSONA u javu ili obrnuto
            //readTree- za citanje JSON fajla i pretvara ga u stablo
            //JsonNode- predstavlja glavni cvor to stabla

            JsonNode reciNode = rootNode.path("naredbe");//u reciNode dodeljuje cvor reci(sadrzi niz svih onih funkcija(SELECT...))

            reci = new ArrayList<>();
            for (JsonNode rec : reciNode) {//svaki element u reciNode je tipa JsonNode tako da mora i rec da bude tog tipa
                reci.add(rec.asText());
            }
            //prolazi kroz sve reci iz reciNode i dodeljuje ih u listu reci
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void proveriUnetiTekst(String tekst) {
        for (String rec : reci) {
            if (tekst.contains(rec)) {
                JLabel label=new JLabel(rec);
                //label.setForeground(Color.BLUE);
                //MyFrame.getTekstPanel().setText(MyFrame.getTextPane().replace(rec,label.setForeground(Color.BLUE));
            }
        }
    }
}
