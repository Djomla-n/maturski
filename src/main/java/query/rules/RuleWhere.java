package query.rules;

import utils.Constants;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RuleWhere implements Rule{
    @Override
    public boolean check(String [] upit) throws SQLException {
        int br=0;

        for (int i = 1; i < upit.length; i++) {
            if(upit[i].equalsIgnoreCase("WHERE")){
                for (int k = br; k < upit.length; k++) {
                    String []pom = upit[k].split("[ ,\"\n\t()]");
                    for (int j = 0; j < pom.length; j++) {
                        if(pom[j].equalsIgnoreCase("AS") || pom[j].equalsIgnoreCase("MIN") ||
                                pom[j].equalsIgnoreCase("MAX") || pom[j].equalsIgnoreCase("COUNT") ||
                                pom[j].equalsIgnoreCase("AVG") || pom[j].equalsIgnoreCase("SUM")){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
