package query.rules;

import gui.view.MyFrame;
import utils.Constants;

import java.sql.*;
import java.util.ArrayList;

public class RuleColumn implements Rule{
    @Override
    public boolean check(String [] upit) throws SQLException {
        return true;
        /*if(upit[1].equalsIgnoreCase("*"))
            return true;
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+ Constants.MYSQL_IP +"/"+Constants.MYSQL_DATABASE
                ,Constants.MYSQL_USERNAME,Constants.MYSQL_PASSWORD);

        DatabaseMetaData metaData = connection.getMetaData();
        int i =0;
        while(!upit[i].equalsIgnoreCase("FROM")){
            i++;
        }
        ///Select ime nesto, prezime from autori
        ///Select ime as nesto, prezime from autori
        ///Select ime as "nesto", prezime from autori
        ///Select ime as "nesto i nesto", prezime from autori

        String imeTabele = upit[i+1];

        ResultSet columns = metaData.getColumns(connection.getCatalog(), null, imeTabele , null);
        return false;*/
    }
}
