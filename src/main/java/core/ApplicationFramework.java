package core;

import javax.swing.*;

import app.AppCore;
import database.MYSQLrepository;
import gui.view.MyFrame;
import resource.data.Row;
import utils.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//okuplja sve interfejse
public class ApplicationFramework {



    private static ApplicationFramework instance;
    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance= new ApplicationFramework();

        }
        return instance;
    }

}
