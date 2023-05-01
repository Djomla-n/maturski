package resource.data;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
//omogucava da se pozove setName
public class Row {

    private String name;//ime redea
    private Map<String, Object> fields;//mapa polja


    public Row() {
        this.fields = new HashMap<>();
    }

    public void addField(String fieldName, Object value) {
        this.fields.put(fieldName, value);
    }//u polje upisuje njegovu vrednost i njegovo ime

    public void removeField(String fieldName) {
        this.fields.remove(fieldName);
    }

}
