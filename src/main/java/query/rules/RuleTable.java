package query.rules;

import utils.Constants;

import java.sql.*;

public class RuleTable implements Rule{
    @Override
    public boolean check(String upit1) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+ Constants.MYSQL_IP +"/"+Constants.MYSQL_DATABASE
                ,Constants.MYSQL_USERNAME,Constants.MYSQL_PASSWORD);
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tabela = metaData.getTables(connection.getCatalog(), null, null,null);

        String [] upit = upit1.split("[ \r\n]");
        int i =0;
        while(!upit[i].equalsIgnoreCase("FROM")){
            i++;
        }
        while(tabela.next()){
            String imeTabele = tabela.getString("TABLE_NAME");
            if(upit[i+1].equals(imeTabele)){
                return true;
            }
        }
        return false;
    }
}
