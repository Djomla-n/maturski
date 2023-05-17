package query.rules;

import resource.enums.Upiti;
import utils.Constants;

import java.sql.*;

public class RuleAlias implements Rule {
    @Override
    public boolean check(String[] upit) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://" + Constants.MYSQL_IP + "/" + Constants.MYSQL_DATABASE
                , Constants.MYSQL_USERNAME, Constants.MYSQL_PASSWORD);

        DatabaseMetaData metaData = connection.getMetaData();//da mogu da vucem vrednosti sa baze

        Upiti[] sqlCommands = Upiti.values();

        int br = 0;
        while (!upit[br].equalsIgnoreCase("FROM")) {
            br++;
        }


        String imeTabele = upit[br];
        ResultSet columns = metaData.getColumns(connection.getCatalog(), null, imeTabele, null);

        while (columns.next()) {
            for (int i = 1; i < br; i++) {
                for (int j = 0; j < br; j++) {
                    String[] pom = upit[j].split("[ ,\"\n\t()]");
                    System.out.println(pom[j]);
                    if (!(pom[j].equals(columns.getString("COLUMN_NAME")) &&
                            !pom[j - 1].equals(columns.getString("COLUMN_NAME")) ||
                            !sqlCommands.toString().equalsIgnoreCase(pom[j - 1]))) {
                        return false;
                    }
                }
            }
        }

        //SELECT nesto as nesto from autori
        return true;
    }
}