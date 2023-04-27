package database;

import lombok.AllArgsConstructor;
import lombok.Data;
import resource.DBNode;
import resource.data.Row;

import java.util.List;

@Data
@AllArgsConstructor//pravi primarni konstruktor koji u this.repository dodeljuje repository
public class DatabaseImplementation implements Database {

    private Repository repository;//pravi promenljivu repository koja sadrzi getSchema i get

    @Override
    public DBNode loadResource() {
        return repository.getSchema();
    }//metoda koja vraca getShemu is repositorija

    @Override
    public List<Row> readDataFromTable(String tableName) {
        return repository.get(tableName);
    }//vraca listu redova iz date tabele
}
