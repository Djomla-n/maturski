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

        String imeTabele = upit[br + 1];
        ResultSet columns = metaData.getColumns(connection.getCatalog(), null, imeTabele, null);

        for (int i = 1; i < br; i++) {
            while (columns.next()) {
                String[] pom = upit[i].split("[ ,\n\t()]");
                for (int k = 0; k < pom.length; k++) {
                    while (pom[k].equals(columns.getString("COLUMN_NAME")) ||
                            pom[k].equalsIgnoreCase(sqlCommands.toString())) {
                        i++;
                        pom = upit[i].split("[ ,\n\t()]");
                    }
                }
                System.out.println("cao");
                if (upit[i].startsWith("\"")) {
                    while (!upit[i].endsWith("\"")) {
                        i++;
                    }

                    if (upit[i + 1].equalsIgnoreCase(sqlCommands.toString()) ||
                            upit[i + 1].equals(columns.getString("COLUMN_NAME"))) {
                        return true;
                    }
                } else if (upit[i + 1].equals(columns.getString("COLUMN_NAME")) ||
                        upit[i + 1].equalsIgnoreCase(sqlCommands.toString())) {
                    i = i + 2;
                    System.out.println("cao");
                    return true;
                }
            }
        }
        return false;
    }
}

