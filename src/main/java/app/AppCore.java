package app;

import core.ApplicationFramework;
import core.Gui;
import database.Database;
import database.DatabaseImplementation;
import database.MYSQLrepository;
import database.settings.Settings;
import database.settings.SettingsImplementation;
import gui.SwingGui;
import gui.table.TableModel;
import lombok.Getter;
import lombok.Setter;
import observer.Notification;
import observer.enums.NotificationCode;
import observer.implementation.PublisherImplementation;
import resource.implementation.InformationResource;
import tree.Tree;
import tree.implementation.TreeImplementation;
import utils.Constants;

import javax.swing.tree.DefaultTreeModel;

@Getter
@Setter
public class AppCore extends PublisherImplementation {

    private Database database;
    private Settings settings;
    private TableModel tableModel;
    private DefaultTreeModel defaultTreeModel;
    private Tree tree;
    private ApplicationFramework applicationFramework;

    public AppCore() {
        this.settings = initSettings();//u settings dodeljuje sve sto je potrebno za konekciju
        this.database = new DatabaseImplementation(new MYSQLrepository(this.settings));
        //pravi objekat MYSQLrepository u koji prosledjuje setting, koji se dalje prosledjuje u objekat klase DatabaseImplementation
        this.tableModel = new TableModel();
        this.tree = new TreeImplementation();
        this.applicationFramework=initaf();
    }
    private ApplicationFramework initaf(){
        ApplicationFramework  af= ApplicationFramework.getInstance();
        Gui gui=new SwingGui();
        af.izvrsi(gui);
        af.run();
        return af;
    }//pokrece gui

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
        return this.tree.generateTree(ir);//u tree dodeljuje taj 'Cvor'
    }

    public void readDataFromTable(String fromTable){

        tableModel.setRows(this.database.readDataFromTable(fromTable));

        //Zasto ova linija moze da ostane zakomentarisana?
        this.notifySubscribers(new Notification(NotificationCode.DATA_UPDATED, this.getTableModel()));
    }




}
