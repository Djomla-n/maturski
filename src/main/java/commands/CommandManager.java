package commands;

import core.ApplicationFramework;
import lombok.Getter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Getter

public class CommandManager {

    private List<Commands> commands = new ArrayList<>();
    private int currentCommand = 0;


    public void addCommand(Commands command){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        doCommand();
    }

    public void doCommand(){
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).doCommand();
            ApplicationFramework.getInstance().enableUndoAction();
        }
        if(currentCommand==commands.size()){
            ApplicationFramework.getInstance().disableRedoAction();
        }
    }

    public void undoCommand(){
        if(currentCommand > 0){
            ApplicationFramework.getInstance().enableRedoAction();
            commands.get(--currentCommand).undoCommand();
        }
        if(currentCommand==0){
            ApplicationFramework.getInstance().disableUndoAction();
        }
    }

}
