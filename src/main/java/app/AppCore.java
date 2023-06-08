package app;

import database.Database;
import database.DatabaseImplementation;
import database.MYSQLrepository;
import database.settings.Settings;
import database.settings.SettingsImplementation;
import gui.table.TableModel;
import gui.view.MyFrame;
import lombok.Getter;
import lombok.Setter;
import observer.Notification;
import observer.enums.NotificationCode;
import observer.implementation.PublisherImplementation;
import query.Checker;
import query.Description;
import resource.data.Row;
import resource.implementation.InformationResource;
import tree.Tree;
import tree.implementation.TreeImplementation;
import utils.Constants;

import javax.swing.tree.DefaultTreeModel;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AppCore extends PublisherImplementation {

    private Database database;
    private Settings settings;
    private TableModel tableModel;
    private DefaultTreeModel defaultTreeModel;
    private Tree tree;
    private Description description = new Description();

    private MYSQLrepository rep;
    private String[] niz;
    List<Row> rows = new ArrayList<>();
    private Checker checker = new Checker();

    public AppCore() {
        this.settings = initSettings();//u settings dodeljuje sve sto je potrebno za konekciju
        this.database = new DatabaseImplementation(new MYSQLrepository(this.settings));
        //pravi objekat MYSQLrepository u koji prosledjuje setting, koji se dalje prosledjuje u objekat klase DatabaseImplementation
        this.tableModel = new TableModel();
        this.tree = new TreeImplementation();
    }
    private Settings initSettings() {
        Settings settingsImplementation = new SettingsImplementation();
        settingsImplementation.addParameter("mysql_ip", Constants.MYSQL_IP);
        settingsImplementation.addParameter("mysql_database", Constants.MYSQL_DATABASE);
        settingsImplementation.addParameter("mysql_username", Constants.MYSQL_USERNAME);
        settingsImplementation.addParameter("mysql_password", Constants.MYSQL_PASSWORD);
        return settingsImplementation;
    }


    public DefaultTreeModel loadResource(){
        InformationResource ir = (InformationResource) this.database.loadResource();//pravi 'Cvor' (roditelj ako ne postoji vise cvorova, dete ako postoji roditelj) i u njega dodeljuje informaciju
        try {
            description.loadJSON();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this.tree.generateTree(ir);//u tree dodeljuje taj 'Cvor'
    }

    public void readDataFromTable(String fromTable){

        tableModel.setRows(this.database.readDataFromTable(fromTable));
        MyFrame.getInstance().getTabelaPanel().setModel(tableModel);
    }

    public boolean proveriUpit(String upit) throws SQLException {
        return checker.check(upit);
    }
    public void posaljiUpit(String upit) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+ Constants.MYSQL_IP +"/"+Constants.MYSQL_DATABASE
                ,Constants.MYSQL_USERNAME,Constants.MYSQL_PASSWORD);
        String pocetan = upit;
        niz= pocetan.split(" ");
        if(niz[0].equalsIgnoreCase("SELECT")){
            PreparedStatement preparedStatement = connection.prepareStatement(upit);
            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

                while(rs.next()){
                    Row row = new Row();

                    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                        row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                    }
                    rows.add(row);
                }
                tableModel.setRows(rows);
                MyFrame.getInstance().getTabelaPanel().setModel(tableModel);
                rows.clear();
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
                finally {
                    connection = null;
                }
        }
        else{
            Statement stmt = connection.createStatement();
            stmt.execute(upit);
        }
    }
}
