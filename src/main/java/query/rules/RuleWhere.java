package query.rules;

import utils.Constants;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RuleWhere implements Rule{
    @Override
    public boolean check(String [] upit) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://" + Constants.MYSQL_IP + "/" + Constants.MYSQL_DATABASE
                , Constants.MYSQL_USERNAME, Constants.MYSQL_PASSWORD);

        DatabaseMetaData metaData = connection.getMetaData();

        int br=0;
        while(!upit[br].equalsIgnoreCase("WHERE")) {
            br++;
        }

        for (int i = br; i < upit.length; i++) {
            String []pom = upit[i].split("[ ,\"\n\t()]");
            System.out.println(pom[0]);
            for (int j = 0; j < pom.length; j++) {
                if(pom[j].equalsIgnoreCase("AS") || pom[j].equalsIgnoreCase("MIN") ||
                        pom[j].equalsIgnoreCase("MAX") || pom[j].equalsIgnoreCase("COUNT") ||
                        pom[j].equalsIgnoreCase("AVG") ){
                    return false;
                }
            }
        }
        return true;
    }
}
