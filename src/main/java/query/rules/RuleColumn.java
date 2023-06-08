package query.rules;

import gui.view.MyFrame;
import resource.enums.Upiti;
import utils.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RuleColumn implements Rule{
    @Override
    public boolean check(String upit1) throws SQLException {
        String [] upit = upit1.split(" ");

        if(upit[1].equalsIgnoreCase("*"))
            return true;
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+ Constants.MYSQL_IP +"/"+Constants.MYSQL_DATABASE
                ,Constants.MYSQL_USERNAME,Constants.MYSQL_PASSWORD);

        DatabaseMetaData metaData = connection.getMetaData();
        int i =0, brZareza = 0;
        Upiti[] sqlCommands = Upiti.values();
        while(!upit[i].equalsIgnoreCase("FROM")){
            i++;
            if(upit[i].contains(","))
                brZareza++;
        }
        //select ime, COUNT(id) , id from autors
        String imeTabele = upit[i+1];
        ResultSet columns = metaData.getColumns(connection.getCatalog(), null, imeTabele , null);

        int j;
        for(i = 0; i < sqlCommands.length;i++){
            j=1;
            while(!upit[j].equalsIgnoreCase("FROM")){
                String []pom = upit[j].split("[ ,\"\n\t()]");
                for(int k = 0; k < pom.length;k++){
                    if(sqlCommands.toString().equalsIgnoreCase(pom[k])){
                        brZareza--;
                    }
                }
                j++;
            }
        }
        int brKolona=0;
        while(columns.next()){
            for(i = 0; i<upit.length; i++){
                String []pom = upit[i].split("[ ,\"\n\t()]");
                for(int k = 0; k < pom.length; k++){
                    if(columns.getString("COLUMN_NAME").equals(pom[k]))
                        brKolona++;
                }
            }
        }
        if(brZareza == 0 && brKolona == 1)
            return true;
        if(brZareza == 0)
            brZareza++;
        if(brZareza + 1 == brKolona)
            return true;
        return false;
    }
}
