package gui.table;

import lombok.Data;
import resource.data.Row;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

@Data
public class TableModel extends DefaultTableModel {

    private List<Row> rows;//pravi listu redova u kojoj svaki element sadrzi ime reda i mapu polja


    private void updateModel(){

        int columnCount = rows.get(0).getFields().keySet().size();
        //uzima prvu vrednost iz liste rows, zatim metoda getFields() pravi niz polja od tog elementa (prvo polje ce da bude za ime reda,
        //a drugo za mapu polja (ime svkakog polja i njegova vrednost)). Onda keySet() prvi argument prilikom definisanja hash mape
        //stavlja kao kljuc u ovom slucaju je to ime reda. I size vraca ukupan broj kljuceva.

        Vector columnVector = DefaultTableModel.convertToVector(rows.get(0).getFields().keySet().toArray());
        //primarni kljucevi se pretvaraju u niz
        // DefaultTableModel se koristi samo za predstavljanje tabele u Swingu.
        //tako da se na kraju niz kluceva pretvara u vektor i dodeljuje u columnVector
        Vector dataVector = new Vector(columnCount);
        //kreira novi vektor koji ima pocetni broj elemenata isto koliko ima primarnih kljuceva

        for (int i=0; i<rows.size(); i++){
            dataVector.add(DefaultTableModel.convertToVector(rows.get(i).getFields().values().toArray()));
            //u dataVector dodeljuje vektore koje sadrze primarne kljuceve (columnVector samo od svakog elementa posebno)
        }
        //zasto se u dataVector prvobitna velicina stavlja onoliko koliko ima primarnih kljuceva u prvom redu, a ne koliko ima redova ukupno,
        //jer se u njega upisuju vektori koji sadrze primarne kljuceve od svakog reda?

        setDataVector(dataVector, columnVector);
        //setDataVector pripada DefaultTableModelu ali nisam siguran sta radi

    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
        updateModel();
    }
}
