package query;

import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Description {
    private String desc;
    private String sugg;
    private String name;
    private static ArrayList<Description> rules;

    public Description(String name, String desc, String sugg){
        this.name=name;
        this.desc=desc;
        this.sugg=sugg;
    }
    public Description(){
    }
    public void loadJSON() throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get("src/main/java/resource/pravila.json")));

        JSONObject jsonObject = new JSONObject(jsonString);
        rules = new ArrayList<>();
        for (String key : jsonObject.keySet()) {
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject rule = jsonArray.getJSONObject(i);
                Description description= new Description(rule.getString("name"), rule.getString("desc"), rule.getString("sgs") );
                rules.add(description);
            }
        }
    }
    public static ArrayList<Description> getRules(){
        return rules;
    }
}
