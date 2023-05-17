package core;

import javax.swing.*;

import app.AppCore;
import commands.CommandManager;
import database.MYSQLrepository;
import gui.view.MyFrame;
import resource.data.Row;
import utils.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//okuplja sve interfejse
public class ApplicationFramework {



    private static ApplicationFramework instance = null;
    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance= new ApplicationFramework();
        }
        return instance;
    }

    public void disableUndoAction() {
        MyFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }

    public void disableRedoAction() {
        MyFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);

    }
    public void enableRedoAction() {
        MyFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
    }

    public CommandManager getCommandManager() {
        return MyFrame.getInstance().getCommandManager();
    }

    public void enableUndoAction() {
        MyFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);

    }

}
