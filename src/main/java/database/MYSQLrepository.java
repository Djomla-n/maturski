package database;

import database.settings.Settings;
import lombok.Data;
import lombok.Getter;
import resource.DBNode;
import resource.data.Row;
import resource.enums.AttributeType;
import resource.implementation.Attribute;
import resource.implementation.Entity;
import resource.implementation.InformationResource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
public class MYSQLrepository implements Repository{

    private Settings settings;
    private Connection connection;

    public MYSQLrepository(Settings settings) {
        this.settings = settings;
    }//primarni konstruktor

    public void initConnection() throws SQLException, ClassNotFoundException{
        String ip = (String) settings.getParameter("mysql_ip");
        String database = (String) settings.getParameter("mysql_database");
        String username = (String) settings.getParameter("mysql_username");
        String password = (String) settings.getParameter("mysql_password");
        connection = DriverManager.getConnection("jdbc:mysql://"+ip+"/"+database,username,password);
    }//konekcija sa bazom uzima vrednoste iz Constants

    private void closeConnection(){
        try{
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connection = null;
        }
    }//zatvara konekciju


    @Override
    public DBNode getSchema() {

        try{
            this.initConnection();//konektuje se na bazu

            DatabaseMetaData metaData = connection.getMetaData();//u metaData dodeljuje (MetaData)objekat koji sadrzi sve podatke iz tabele
            InformationResource ir = new InformationResource("Maturski");//pravi glavni cvor u koji dodeljuje ime

            String tableType[] = {"TABLE"};
            ResultSet tables = metaData.getTables(connection.getCatalog(), null, null, tableType);
            //pravi objekat tipa ResultSet koji vraca tabelu
            //prvi argument predstavlja ime baze podataka za koju se dobijaju informacije o tabelama (connection.getCatalog() vraca ime trenutne baze podataka)
            //drugi i treci nista
            //cetvrti sta zelimo da nam vrate "TABLE", "VIEW"...

            while (tables.next()){//dok postoji sledeca tabela

                String tableName = tables.getString("TABLE_NAME");//u tableName dodeljujemo ime tabele
                if(tableName.contains("trace"))continue;//ako je ime tabele trace preskoci je
                Entity newTable = new Entity(tableName, ir);//pravi cvor (ime cvora je tableName a njen roditelj je Matusrski (glavni cvor)
                ir.addChild(newTable);//dodaje taj cvor

                ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableName, null);
                //prvi argument je ime baze podataka, treci kaze da gleda samo tabele koje se zovu tableName

                while (columns.next()){//dok postoje sledece kolone

                    // COLUMN_NAME TYPE_NAME COLUMN_SIZE ....

                    String columnName = columns.getString("COLUMN_NAME");//u columnName dodeljuje ime kolone
                    String columnType = columns.getString("TYPE_NAME");//u columnType dodeljuje ime tipa kolone


                    int columnSize = Integer.parseInt(columns.getString("COLUMN_SIZE"));//u columnSize dodeljuje velicinu same kolone

                    Attribute attribute = new Attribute(columnName, newTable,
                            AttributeType.valueOf(
                                    Arrays.stream(columnType.toUpperCase().split(" "))
                                            .collect(Collectors.joining("_"))),
                            columnSize);
                    newTable.addChild(attribute);
                    //pravi objekat attribute kojem prosledjuje njgovo ime, roditelja (to ce biti Entity),
                    //tip podataka pretvara vrednosti iz enum fajla u niz iz kojeg se kreira stream (mogu se koristiti stream opcije nad elementima niza)
                    //ime tipa kolone pretvara u velika slova, a zatim ih deli po razmaku.
                    //Onda metoda collect iz streama prikuplja sve dobiijene podatke spaja koristecu _
                    //npr ako nam columnType sadrzi int unsigned prvo ga pretvaramo u INT UNSIGNED, zatim ga delimo po razmaku ondosno sada imamo samo INT i samo UNSIGNED
                    //i na kraju skupljamo oba ta sto smo dobili i spajamo ih koristeci _ tako da dobijamo INT_UNSIGNED sto nam se podudara sa onim iz enum fajla
                    //na kraju dodeljujemo samo broj redova
                }



            }


            //TODO Ogranicenja nad kolonama? Relacije?


            return ir;// na kraju vracamo glavni cvor koji sadrzi sve svoje pod cvorove
            //String isNullable = columns.getString("IS_NULLABLE");
            // ResultSet foreignKeys = metaData.getImportedKeys(connection.getCatalog(), null, table.getName());
            // ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, table.getName());

        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        catch (ClassNotFoundException e2){
            e2.printStackTrace();}
        finally {
            this.closeConnection();//zatvaramo konekciju
        }

        return null;
    }

    @Override
    public List<Row> get(String from) {

        List<Row> rows = new ArrayList<>();


        try{
            this.initConnection();//konektuje se sa bazom

            String query = "SELECT * FROM " + from;//pravljenje upita
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //PreparedStatement efikasnije izvrsavanje upita nad bazom
            //u prparedStatement se dodeljuje cela tabela cije je ime deklarisano u samom pozivu metode
            ResultSet rs = preparedStatement.executeQuery();
            //ispisivanje samog preparedStatement-a
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            //u resultSetMetaData dodeljuju se podatci u kolonama ime, tip podataka, velicina...

            if (rs.next()) {
                do {//dok postoji sledeci red

                    Row row = new Row();//pravi objekat row
                    //row.setName("CAO");//u ime reda dodeljuje ime same kolone

                    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                        row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                    }//dodeljuje polja u red
                    rows.add(row);//u listu redova dodeljuje red
                }while(rs.next());
            }
            else{
                Row row = new Row();//pravi objekat row
                row.setName(from);//u ime reda dodeljuje ime tabele
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    row.addField(resultSetMetaData.getColumnName(i), null);
                }//dodeljuje polja u red
                rows.add(row);//u listu redova dodeljuje red
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.closeConnection();
        }

        return rows;
    }
}
